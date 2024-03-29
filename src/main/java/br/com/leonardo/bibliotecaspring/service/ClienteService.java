package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.formatter.Formatter;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteConverter clienteConverter;

   public ClienteDTO localizarPeloId(Long id){
       ClienteDTO clienteDto = this.clienteConverter.toDto(this.clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException()));

       clienteDto.setCpf(Formatter.cpfMask(clienteDto));

       return clienteDto;
        }

    public Iterable<ClienteDTO> localizarTodos(){
        List<ClienteDTO> listaCliente =  this.clienteRepository.findAll()
              .stream()
              .map(clienteConverter::toDto).toList();

        Formatter.cpfMaskLista(listaCliente);

        return listaCliente;
    }

    public Cliente cadastrarCliente(Cliente cliente){
       return this.clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id){
       clienteRepository.deleteById(id);
    }

    public Cliente editarCliente(Long id, Cliente novoCliente){
           Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cliente nao econtrado"));
           cliente.setCpf(novoCliente.getCpf());
           cliente.setNome(novoCliente.getNome());
           cliente.setGenero(novoCliente.getGenero());
           cliente.setEndereco(novoCliente.getEndereco());
           cliente.setTelefone(novoCliente.getTelefone());
           cliente.setDataNascimento(novoCliente.getDataNascimento());
           return clienteRepository.save(cliente);

    }


}
