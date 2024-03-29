package br.com.leonardo.bibliotecaspring.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
