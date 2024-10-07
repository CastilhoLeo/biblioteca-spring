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
public class SalvarEntradaRequest {

    private long idLivro;

    private int quantidade;

    private LocalDate data;
}
