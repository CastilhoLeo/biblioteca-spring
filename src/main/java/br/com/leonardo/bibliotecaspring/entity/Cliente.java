package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.enums.Genero;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table (name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nome;

    private String sobrenome;

    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente",  fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Endereco> endereco = new ArrayList<Endereco>();

    @CPF(message = "CPF invalido")
    private String cpf;

    @Length(min = 11, max = 11, message = "O número de celular está inválido!")
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Genero genero;
}
