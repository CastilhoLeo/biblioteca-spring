package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock ClienteConverter clienteConverter;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void clienteService_CadastratCliente_ReturnsSavedCliente(){
        Cliente clienteToSave = ClienteBuilder.umCliente().agora();

        when(clienteRepository.save(Mockito.any())).thenReturn(clienteToSave);

        Cliente savedCliente = clienteService.cadastrarCliente(clienteToSave);
        System.out.println(savedCliente);

        Assertions.assertThat(savedCliente).isNotNull();
    }

    public void clienteService_FindById_ReturnCliente() {

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(new ArrayList<Endereco>())
                .telefone("44998240563").build();

        clienteRepository.save(cliente);

        when(clienteConverter.toDto(clienteRepository.findById(cliente.getId())
                .orElseThrow(()-> new EntityNotFoundException())));
        
    }

}
