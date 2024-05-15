package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Pageable pageable;


    @Test
    @Order(1)
    @Transactional
    public void pesquisaDinamica_deveRetornarOsClientes(){

        clienteRepository.save(ClienteBuilder.umCliente().agora());
        clienteRepository.save(ClienteBuilder.umCliente().comId(2L).comNome("Joao").comCpf("23852633001").agora());
        clienteRepository.save(ClienteBuilder.umCliente().comId(3L).comNome("Maria").comCpf("41126661015").agora());

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

        clienteRepository.save(ClienteBuilder.umCliente().agora());

        Page<Cliente> pageCliente = clienteRepository.pesquisaDinamica("leonardo", "", pageable );

        pageCliente.getContent().stream().forEach(cliente -> System.out.println(cliente));

        Assertions.assertThat(pageCliente)
                .extracting(Cliente::getId)
                .hasSize(1)
                .containsExactlyInAnyOrder(4L);
    }

}
