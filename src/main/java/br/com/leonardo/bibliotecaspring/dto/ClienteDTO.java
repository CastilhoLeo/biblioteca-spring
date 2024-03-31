package br.com.leonardo.bibliotecaspring.dto;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private List<Endereco> endereco = new ArrayList<Endereco>();
    private String cpf;
    private String telefone;
    private Genero genero;

}
