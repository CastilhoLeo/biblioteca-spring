package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Min(value = 0, message = "O estoque ficará negativo!")
    private int estoqueAtual;

    private int estoqueInicial;

    @Enumerated(EnumType.STRING)
    private SituacaoLivro situacaoLivro;

    @OneToOne
    @JoinColumn(name = "livro_id")
    @JsonIgnore
    private Livro livro;

}
