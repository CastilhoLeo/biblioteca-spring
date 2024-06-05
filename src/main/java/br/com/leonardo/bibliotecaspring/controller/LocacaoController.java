package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @PostMapping("/cadastro")
    public ResponseEntity<Locacao> cadastrarLocacao(@RequestBody Locacao locacao){
        return ResponseEntity.ok().body(locacaoService.salvarLocacao(locacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locacao> devolverLocacao(@PathVariable Long id){
        return ResponseEntity.ok().body(locacaoService.devolverLocacao(id));
    }
}
