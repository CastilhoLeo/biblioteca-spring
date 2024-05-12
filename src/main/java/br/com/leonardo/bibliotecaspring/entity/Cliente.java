package br.com.leonardo.bibliotecaspring.entity;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String nome;
    private String sobrenome;
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Endereco> endereco = new ArrayList<Endereco>();
    @CPF(message = "CPF invalido")
    private String cpf;
    @Length(min = 11, max = 11, message = "O número de celular está inválido!")
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Genero genero;
}
