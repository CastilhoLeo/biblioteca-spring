package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> localizarPeloId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.pesquisarPeloId(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody LivroDTO livro){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> editarCliente(@PathVariable Long id, @RequestBody LivroDTO clienteEditado){
       return ResponseEntity.accepted().body(service.editarLivro(id, clienteEditado));
    }

    @GetMapping("")
    public ResponseEntity<Page<LivroDTO>> pesquisaDinamica(@RequestParam(value = "titulo", required = false) String titulo,
                                                           @RequestParam(value = "autor", required = false) String autor,
                                                           Pageable pageable){
        return ResponseEntity.ok().body(service.pesquisDinamica(titulo, autor, pageable));
    }



}
