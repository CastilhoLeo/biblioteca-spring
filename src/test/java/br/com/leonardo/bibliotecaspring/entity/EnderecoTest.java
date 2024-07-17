package br.com.leonardo.bibliotecaspring.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class EnderecoTest {
    @Test
    public void deveInstanciarEndereco(){
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setRua("Olimpio Mendes da Rocha");
        endereco.setNumero("355");
        endereco.setCep("87023015");
        endereco.setComplemento("bl 4 ap 801");
        endereco.setBairro("Cidade Nova");
        endereco.setCliente(new Cliente());

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals(Endereco.class, endereco.getClass());
        Assertions.assertEquals(endereco.getId(), 1L);
        Assertions.assertEquals(endereco.getRua(), "Olimpio Mendes da Rocha");
        Assertions.assertEquals(endereco.getNumero(), "355");
        Assertions.assertEquals(endereco.getCep(), "87023015");
        Assertions.assertEquals(endereco.getComplemento(), "bl 4 ap 801");
        Assertions.assertEquals(endereco.getBairro(), "Cidade Nova");
        Assertions.assertEquals(endereco.getCliente().getClass(), Cliente.class);
        Assertions.assertNotNull(endereco.getCliente());
    }






}
