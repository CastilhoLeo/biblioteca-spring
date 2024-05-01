package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.Genero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteTest {

    @Test
    public void deveInstanciarCliente(){
        Cliente cliente = new Cliente();
                cliente.setId(1L);
                cliente.setNome("Leonardo");
                cliente.setSobrenome("Castilho");
                cliente.setDataNascimento(LocalDate.of(1992,11,13));
                cliente.setEndereco(new ArrayList<Endereco>());
                cliente.setCpf("41861297890");
                cliente.setTelefone("44998240563");
                cliente.setGenero(Genero.MASCULINO);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(Cliente.class, cliente.getClass());
        Assertions.assertEquals(cliente.getId(), 1L);
        Assertions.assertEquals(cliente.getNome(), "Leonardo");
        Assertions.assertEquals(cliente.getSobrenome(), "Castilho");
        Assertions.assertEquals(cliente.getDataNascimento(), LocalDate.of(1992,11,13));
        Assertions.assertEquals(cliente.getEndereco().size(), 0);
        Assertions.assertEquals(cliente.getCpf(), "41861297890");
        Assertions.assertEquals(cliente.getTelefone(), "44998240563");
        Assertions.assertEquals(cliente.getGenero(), Genero.MASCULINO);
    }
}
