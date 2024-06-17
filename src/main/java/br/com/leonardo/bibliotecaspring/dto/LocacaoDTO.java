package br.com.leonardo.bibliotecaspring.dto;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocacaoDTO {

    private ClienteDTO clienteDto;

    private LivroDTO livroDto;

    private PrazoLocacao prazoLocacao;

    private LocalDate dataSaida;

    private LocalDate dataPrevistaDevolucao;

    private LocalDate dataEfetivaDevolucao;
}
