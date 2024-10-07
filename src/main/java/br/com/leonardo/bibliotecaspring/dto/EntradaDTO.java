package br.com.leonardo.bibliotecaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntradaDTO {

    private Long id;

    private LivroDTO livroDTO;

    private int quantidade;

    private LocalDate data;
}
