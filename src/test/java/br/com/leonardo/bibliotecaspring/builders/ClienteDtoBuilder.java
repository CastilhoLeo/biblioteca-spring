package br.com.leonardo.bibliotecaspring.builders;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDtoBuilder {

    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private List<EnderecoDTO> endereco = new ArrayList<>();
    private String cpf;
    private String telefone;
    private Genero genero;

    private ClienteDtoBuilder(){
    }

    private static void inicializarDadosPadroes(ClienteDtoBuilder builder) {
        builder.nome = "Leonardo";
        builder.sobrenome = "Castilho";
        builder.dataNascimento = LocalDate.of(1992,11,13);
        builder.endereco = new ArrayList<EnderecoDTO>();
        builder.cpf = "41861297890";
        builder.telefone = "44998240563";
        builder.genero = Genero.MASCULINO;
    }
    public static ClienteDtoBuilder umCliente(){
        ClienteDtoBuilder builder = new ClienteDtoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public ClienteDTO agora(){
        return new ClienteDTO(id,nome,sobrenome,dataNascimento,endereco,cpf,telefone,genero);
    }

    public Long getId() {
        return id;
    }

    public ClienteDtoBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ClienteDtoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public ClienteDtoBuilder comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public ClienteDtoBuilder comDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public List<EnderecoDTO> getEndereco() {
        return endereco;
    }

    public ClienteDtoBuilder comEndereco(List<EnderecoDTO> endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public ClienteDtoBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ClienteDtoBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Genero getGenero() {
        return genero;
    }

    public ClienteDtoBuilder comGenero(Genero genero) {
        this.genero = genero;
        return this;
    }
}