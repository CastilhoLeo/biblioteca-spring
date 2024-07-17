package br.com.leonardo.bibliotecaspring.converter;


import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.dto.LocacaoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class LocacaoConverterTest {

    @Autowired
    private LocacaoConverter locacaoConverter;

    @Test
    public void devePassarUmaLocacaoParaLocacaoDto(){

        Livro livro = new Livro();
        Cliente cliente = new Cliente();

        Locacao locacao = new Locacao(
                1L,
                cliente,
                livro,
                PrazoLocacao.DIAS_15,
                LocalDate.of(2024,06,21),
                LocalDate.of(2024,07,06),
                LocalDate.of(2024,07,06));

        LocacaoDTO locacaoDto = locacaoConverter.toDto(locacao);

        Assertions.assertNotNull(locacaoDto);
        Assertions.assertEquals(locacaoDto.getClass(), LocacaoDTO.class);
        Assertions.assertEquals(locacaoDto.getPrazoLocacao(),PrazoLocacao.DIAS_15 );
        Assertions.assertEquals(locacaoDto.getClienteDto(),new ClienteDTO());
        Assertions.assertEquals(locacaoDto.getClienteDto().getClass(), ClienteDTO.class);
        Assertions.assertEquals(locacaoDto.getLivroDto(),new LivroDTO());
        Assertions.assertEquals(locacaoDto.getLivroDto().getClass(), LivroDTO.class);
        Assertions.assertEquals(locacaoDto.getDataSaida(),LocalDate.of(2024,06,21) );
        Assertions.assertEquals(locacaoDto.getDataPrevistaDevolucao(),LocalDate.of(2024,07,06));
        Assertions.assertEquals(locacaoDto.getDataEfetivaDevolucao(),LocalDate.of(2024,07,06));
    }

    @Test
    public void devePassarUmaLocacaoDtoParaEntity(){

        LivroDTO livro = new LivroDTO();
        ClienteDTO cliente = new ClienteDTO();

        LocacaoDTO locacaoDto = new LocacaoDTO(
                cliente,
                livro,
                PrazoLocacao.DIAS_15,
                LocalDate.of(2024,06,21),
                LocalDate.of(2024,07,06),
                LocalDate.of(2024,07,06));

        Locacao locacao = locacaoConverter.toEntity(locacaoDto);

        Assertions.assertNotNull(locacao);
        Assertions.assertEquals(locacao.getClass(), Locacao.class);
        Assertions.assertEquals(locacao.getPrazoLocacao(),PrazoLocacao.DIAS_15 );
        Assertions.assertEquals(locacao.getCliente(),new Cliente());
        Assertions.assertEquals(locacao.getCliente().getClass(), Cliente.class);
        Assertions.assertEquals(locacao.getLivro(),new Livro());
        Assertions.assertEquals(locacao.getLivro().getClass(), Livro.class);
        Assertions.assertEquals(locacao.getDataSaida(),LocalDate.of(2024,06,21) );
        Assertions.assertEquals(locacao.getDataPrevistaDevolucao(),LocalDate.of(2024,07,06));
        Assertions.assertEquals(locacao.getDataEfetivaDevolucao(),LocalDate.of(2024,07,06));
    }
}
