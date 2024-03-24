package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.model.Cliente;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(){
    }


}
