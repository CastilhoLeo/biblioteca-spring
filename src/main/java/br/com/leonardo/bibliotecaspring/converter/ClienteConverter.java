package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
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

}
