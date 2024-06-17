package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Pageable pageable;

    @BeforeAll
    public static void setup(@Autowired ClienteRepository clienteRepository){
        clienteRepository.saveAll(List.of(
                ClienteBuilder.cliente().comId(1L).build(),
                ClienteBuilder.cliente().comId(2L).comNome("Joao").comCpf("23852633001").build(),
                ClienteBuilder.cliente().comId(3L).comNome("Maria").comCpf("41126661015").build()
        ));
    }

    @Test
    @Order(1)
    @Transactional
    public void pesquisaDinamica_deveRetornarOsClientes(){


        Page<Cliente> pageCliente = clienteRepository.pesquisaDinamica("a", "", pageable );

        Assertions.assertThat(pageCliente)
                .extracting(Cliente::getId)
                .hasSize(3)
                .containsExactlyInAnyOrder(1L, 2L, 3L);
    }

    @Test
    @Order(2)
    @Transactional
    public void pesquisaDinamica_deveRetornarUmClienteLeonardo(){


        Page<Cliente> pageCliente = clienteRepository.pesquisaDinamica("leonardo", "", pageable );

        Assertions.assertThat(pageCliente)
                .extracting(Cliente::getId)
                .hasSize(1)
                .containsExactlyInAnyOrder(1L);
    }

}
