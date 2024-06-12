package br.com.leonardo.bibliotecaspring.builders;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteBuilder {

    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private List<Endereco> endereco = new ArrayList<>();
    private String cpf;
    private String telefone;
    private Genero genero;
    private Set<Locacao> locacao = new HashSet<Locacao>();

    private ClienteBuilder(){
    }

    private static void inicializarDadosPadroes(ClienteBuilder builder) {
        builder.id =1L ;
        builder.nome = "Leonardo";
        builder.sobrenome = "Castilho";
        builder.dataNascimento = LocalDate.of(1992,11,13);
        builder.endereco = new ArrayList<Endereco>();
        builder.cpf = "41861297890";
        builder.telefone = "44998240563";
        builder.genero = Genero.MASCULINO;
        builder.locacao = new HashSet<Locacao>();
    }
    public static ClienteBuilder umCliente(){
        ClienteBuilder builder = new ClienteBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public Cliente agora(){
        return new Cliente(id,nome,sobrenome,dataNascimento,endereco,cpf,telefone,genero,locacao);
    }

    public Long getId() {
        return id;
    }

    public ClienteBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public ClienteBuilder comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Set<Locacao> getLocacao() {
        return locacao;
    }

    public void comLocacao(Set<Locacao> locacao) {
        this.locacao = locacao;
    }

    public ClienteBuilder comDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public ClienteBuilder comEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public ClienteBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ClienteBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Genero getGenero() {
        return genero;
    }

    public ClienteBuilder comGenero(Genero genero) {
        this.genero = genero;
        return this;
    }
}

