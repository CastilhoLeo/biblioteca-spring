package br.com.leonardo.bibliotecaspring.dto;

import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {

    private int estoqueAtual;

    private int estoqueInicial;

    private SituacaoLivro situacaoLivro;
}
