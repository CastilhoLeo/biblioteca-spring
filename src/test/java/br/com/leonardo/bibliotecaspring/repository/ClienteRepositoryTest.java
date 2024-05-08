package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Pageable pageable;

    @BeforeEach
    public void setup(){

        pageable = PageRequest.of(0,10);

        Cliente c1 = ClienteBuilder.umCliente().agora();
        Cliente c2 = ClienteBuilder.umCliente().comId(2L).comNome("Joao").comCpf("23852633001").agora();
        Cliente c3 = ClienteBuilder.umCliente().comId(3L).comNome("Maria").comCpf("41126661015").agora();

        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);
    }

    @Test
    public void pesquisaDinamica_deveRetornarOsClientes(){

        Page<Cliente> pageCliente = clienteRepository.pesquisaDinamica("a", null, pageable );

        Assertions.assertThat(pageCliente)
                .extracting(Cliente::getId)
                .hasSize(3)
                .containsExactlyInAnyOrder(1L, 2L, 3L);
    }

}
