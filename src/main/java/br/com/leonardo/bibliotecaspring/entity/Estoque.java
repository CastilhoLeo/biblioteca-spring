package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "estoque_atual")
    @Min(value = 0, message = "O estoque ficará negativo!")
    private int estoqueAtual;

    @Column(name = "estoque_inicial")
    @Min(value = 0, message = "O estoque atual ficará negativo")
    private int estoqueInicial;

    @Column(name = "situacao_livro")
    @Enumerated(EnumType.STRING)
    private SituacaoLivro situacaoLivro;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

}
