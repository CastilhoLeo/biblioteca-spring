package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
       ClienteDTO clienteDto = this.clienteConverter.toDto(this.clienteRepository.findById(id)
               .orElseThrow(()-> new ValidationException("Id não localizado!")));

       return clienteDto;
        }


    public List<ClienteDTO> localizarTodos(){
        List<ClienteDTO> listaCliente =  this.clienteRepository.findAll()
              .stream()
              .map(clienteConverter::toDto)
                .toList();

        return listaCliente;
    }

    public Cliente cadastrarCliente(ClienteDTO clienteDTO){
       Cliente cliente = this.clienteConverter.toEntity(clienteDTO);
       return this.clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id){
       clienteRepository.deleteById(id);
    }

    public Cliente editarCliente(Long id, ClienteDTO novoCliente){
           Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ValidationException("Cliente nao encontrado"));
           cliente.setCpf(novoCliente.getCpf());
           cliente.setNome(novoCliente.getNome());
           cliente.setGenero(novoCliente.getGenero());
           cliente.setEndereco(novoCliente.getEndereco());
           cliente.setTelefone(novoCliente.getTelefone());
           cliente.setDataNascimento(novoCliente.getDataNascimento());
           return clienteRepository.save(cliente);
    }

    public Page<ClienteDTO> pesquisaDinamica(String nome, String cpf,  Pageable pageable){
       if(nome!=null || cpf!=null) {
           Page<ClienteDTO> pageClienteDto = this.clienteRepository.pesquisaDinamica(nome, cpf, pageable)
                   .map(cliente -> this.clienteConverter.toDto(cliente));
           return pageClienteDto;
       }

       Page<ClienteDTO> pageClienteDto = this.clienteRepository
               .findAll(pageable).map(cliente->clienteConverter.toDto(cliente));
       return pageClienteDto;
    }

}
