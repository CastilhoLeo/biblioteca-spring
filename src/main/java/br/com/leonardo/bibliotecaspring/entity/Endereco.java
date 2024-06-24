package br.com.leonardo.bibliotecaspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;


@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    private String rua;

    private String numero;

    @Length(min = 8, max = 8, message = "Este CEP é inválido!")
    private String cep;

    private String complemento;

    private String bairro;

    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private Cliente cliente;

}
