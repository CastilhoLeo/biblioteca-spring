package br.com.leonardo.bibliotecaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;
    private String rua;
    private String numero;
    private String cep;
    private String complemento;
    private String bairro;

}
