package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.dto.EntradaDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarEntradaRequest;
import br.com.leonardo.bibliotecaspring.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @PostMapping("/entrada")
    public ResponseEntity<EntradaDTO> salvarEntrada(@RequestBody SalvarEntradaRequest salvarEntradaRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(entradaService.salvarEntrada(salvarEntradaRequest));

    }
}
