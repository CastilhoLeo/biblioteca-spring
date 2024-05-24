package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClienteConverter {


    public ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getDataNascimento(),
                cliente.getEndereco(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getGenero());
    }


    public Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setSobrenome(clienteDTO.getSobrenome());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setGenero(clienteDTO.getGenero());
        return cliente;
    }

}
