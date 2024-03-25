package br.com.leonardo.bibliotecaspring.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "cliente")
    @Cascade(CascadeType.ALL)
    private List<Endereco> endereco;
    @CPF(message = "CPF invalido")
    private String cpf;
    @Length(min = 11, max = 11, message = "O número de celular está inválido!")
    private String telefone;


}
