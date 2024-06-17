package br.com.leonardo.bibliotecaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroLivroRequest {

    private LivroDTO livroDto;
    private int estoqueInicial;
}
