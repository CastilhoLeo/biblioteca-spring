package br.com.leonardo.bibliotecaspring.dto;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private List<EnderecoDTO> endereco = new ArrayList<>();
    private String cpf;
    private String telefone;
    private Genero genero;

}

