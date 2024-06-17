package br.com.leonardo.bibliotecaspring.dto;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO {

    private Long id;

    private String rua;

    private String numero;

    private String cep;

    private String complemento;

    private String bairro;

}
