package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import net.bytebuddy.dynamic.DynamicType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    public void ClienteRepository_Save_ReturnSavedCliente() {

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(List.of(new Endereco()))
                .telefone("44998240563").build();


        Cliente savedCliente = clienteRepository.save(cliente);

        Assertions.assertThat(savedCliente).isNotNull();
        Assertions.assertThat(savedCliente.getNome()).isEqualTo("Leonardo");
        Assertions.assertThat(savedCliente.getSobrenome()).isEqualTo("Castilho");
        Assertions.assertThat(savedCliente.getCpf()).isEqualTo("41861297890");
        Assertions.assertThat(savedCliente.getGenero()).isEqualTo(Genero.MASCULINO);
        Assertions.assertThat(savedCliente.getDataNascimento().isEqual(LocalDate.of(1992,11,13)));
        Assertions.assertThat(savedCliente.getEndereco()).isNotEmpty();
        Assertions.assertThat(savedCliente.getTelefone()).isEqualTo("44998240563");
    }

    @Test
    public void ClienteRepository_FindAll_ReturnMoreThanOneCliente(){

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(new ArrayList<Endereco>())
                .telefone("44998240563").build();

        Cliente cliente2 = Cliente.builder()
                .nome("Lais")
                .sobrenome("Facchini")
                .cpf("88651480555")
                .genero(Genero.FEMININO)
                .dataNascimento(LocalDate.of(1995, 10, 04))
                .endereco(new ArrayList<>())
                .telefone("44997840256").build();


        clienteRepository.save(cliente);
        clienteRepository.save(cliente2);

        List<Cliente> clienteList = clienteRepository.findAll();

        Assertions.assertThat(clienteList).isNotNull();
        Assertions.assertThat(clienteList.size()).isEqualTo(2);
    }

    @Test
    public void ClienteRepository_GetById_ReturnClienteById() {

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(new ArrayList<Endereco>())
                .telefone("44998240563").build();

        clienteRepository.save(cliente);

        Cliente findedCliente = clienteRepository.getById(cliente.getId());

        Assertions.assertThat(findedCliente).isNotNull();
        Assertions.assertThat(findedCliente.getNome()).isEqualTo("Leonardo");
    }
    @Test
    public void ClienteRepository_Update_ReturnClienteWithNameUpdated() {

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(new ArrayList<Endereco>())
                .telefone("44998240563").build();

        clienteRepository.save(cliente);

        Cliente updateCliente = clienteRepository.getById(cliente.getId());

        updateCliente.setNome("Roberto");

        Cliente updatedCliente = clienteRepository.save(updateCliente);

        Assertions.assertThat(updatedCliente.getNome()).isEqualTo("Roberto");
    }

    @Test
    public void ClienteRepository_Delete_ReturnNull() {

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .sobrenome("Castilho")
                .cpf("41861297890")
                .genero(Genero.MASCULINO)
                .dataNascimento(LocalDate.of(1992, 11, 13))
                .endereco(new ArrayList<Endereco>())
                .telefone("44998240563").build();

        clienteRepository.save(cliente);

        clienteRepository.deleteById(cliente.getId());

        Optional<Cliente> deletedCliente = clienteRepository.findById(cliente.getId());

        Assertions.assertThat(deletedCliente).isEmpty();

    }
}
