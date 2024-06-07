package br.com.leonardo.bibliotecaspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String titulo;

    private String autor;

    @Temporal(TemporalType.DATE)
    private LocalDate dataPublicacao;

    private int edicao;

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Estoque estoque;
    
    @JsonIgnore
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Locacao> locacoes = new HashSet<>();

}
