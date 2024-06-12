package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


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


    public Cliente cadastrarCliente(ClienteDTO clienteDTO){
       Cliente cliente = this.clienteConverter.toEntity(clienteDTO);
       cliente.getEndereco().forEach(endereco-> endereco.setCliente(cliente));
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

       if(nome == "" && cpf == "" ) {
           Page<ClienteDTO> pageClienteDto = this.clienteRepository.findAll(pageable).map(clienteConverter::toDto);
           return pageClienteDto;
       }

        Page<ClienteDTO> pageClienteDto = this.clienteRepository.pesquisaDinamica(nome, cpf, pageable)
                .map(clienteConverter::toDto);
        return pageClienteDto;
    }

}
