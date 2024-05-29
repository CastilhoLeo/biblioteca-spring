package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import jakarta.persistence.*;
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
    private int quantidade;
    private int estoqueInicial;
    private SituacaoLivro situacaoLivro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
}
