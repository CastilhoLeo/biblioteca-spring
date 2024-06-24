package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteConverter {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EnderecoConverter enderecoConverter;

    public ClienteDTO toDto(Cliente cliente){

        ClienteDTO clienteDTO =  mapper.map(cliente, ClienteDTO.class);

        List<EnderecoDTO> enderecoDTO =  cliente.getEndereco().stream().map((endereco)->enderecoConverter.toDto(endereco)).toList();

        clienteDTO.setEndereco(enderecoDTO);

        return clienteDTO;
    }

    public Cliente toEntity(ClienteDTO clienteDto){

        Cliente cliente =  mapper.map(clienteDto, Cliente.class);

        List<Endereco> endereco =  clienteDto.getEndereco().stream().map((enderecoDto)->enderecoConverter.toEntity(enderecoDto)).toList();

        cliente.setEndereco(endereco);

        return cliente;

    }

}
