package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteConverter clienteConverter;

   public ClienteDTO localizarPeloId(Long id){
       ClienteDTO clienteDto = this.clienteConverter.toDto(this.clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException()));
       return clienteDto;
        }

    public Iterable<ClienteDTO> localizarTodos(){
      return this.clienteRepository.findAll().stream().map(clienteConverter::toDto).toList();
    }

    public void cadastrarCliente(Cliente cliente){
       clienteRepository.save(cliente);
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
