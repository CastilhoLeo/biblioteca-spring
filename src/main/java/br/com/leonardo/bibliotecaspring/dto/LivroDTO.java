package br.com.leonardo.bibliotecaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private int edicao;
}