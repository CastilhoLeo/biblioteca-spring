package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @Column(name = "prazo_locacao")
    @Enumerated(EnumType.STRING)
    private PrazoLocacao prazoLocacao;

    @Column(name = "data_saida")
    @Temporal(TemporalType.DATE)
    private LocalDate dataSaida;

    @Column(name = "data_prevista_devolucao")
    @Temporal(TemporalType.DATE)
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "data_efetiva_devolucao")
    @Temporal(TemporalType.DATE)
    private LocalDate dataEfetivaDevolucao;


}
