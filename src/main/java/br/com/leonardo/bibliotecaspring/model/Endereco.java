package br.com.leonardo.bibliotecaspring.model;

import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

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

    public Endereco(){
    }
    public Endereco(Long id, String rua, String numero, String cep, String complemento, String bairro) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
    }
}
