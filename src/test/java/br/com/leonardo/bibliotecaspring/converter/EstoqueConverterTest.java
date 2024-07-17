package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EstoqueConverterTest {

    @Autowired
    private EstoqueConverter estoqueConverter;

    @Test
    public void toDto_DevePassarUmEstoqueParaDto(){

        Estoque estoque = new Estoque(1L, 10, 10, SituacaoLivro.DISPONIVEL, new Livro());

        EstoqueDTO estoqueDto = estoqueConverter.toDto(estoque);

        Assertions.assertEquals(estoqueDto.getClass(), EstoqueDTO.class);
        Assertions.assertNotNull(estoqueDto);
        Assertions.assertEquals(estoqueDto.getEstoqueInicial(), 10);
        Assertions.assertEquals(estoqueDto.getEstoqueAtual(), 10);
        Assertions.assertEquals(estoqueDto.getSituacaoLivro(), SituacaoLivro.DISPONIVEL);
    }

    @Test
    public void toEntity_DevePassarUmEstoqueDtoParaEntity(){

        EstoqueDTO estoqueDto = new EstoqueDTO(10, 10, SituacaoLivro.DISPONIVEL);

        Estoque estoque = estoqueConverter.toEntity(estoqueDto);

        Assertions.assertEquals(estoque.getClass(), Estoque.class);
        Assertions.assertNotNull(estoque);
        Assertions.assertEquals(estoque.getId(), null);
        Assertions.assertEquals(estoque.getEstoqueInicial(), 10);
        Assertions.assertEquals(estoque.getEstoqueAtual(), 10);
        Assertions.assertEquals(estoque.getSituacaoLivro(), SituacaoLivro.DISPONIVEL);
    }

}
