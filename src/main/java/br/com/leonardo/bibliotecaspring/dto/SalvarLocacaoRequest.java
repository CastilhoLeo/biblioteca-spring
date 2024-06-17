package br.com.leonardo.bibliotecaspring.dto;

import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalvarLocacaoRequest {

    private Long idCliente;

    private Long idLivro;

    private PrazoLocacao prazoLocacao;
}
