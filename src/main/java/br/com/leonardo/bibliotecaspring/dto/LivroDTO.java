package br.com.leonardo.bibliotecaspring.dto;

import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private int edicao;
    private EstoqueDTO estoqueDto;
}
