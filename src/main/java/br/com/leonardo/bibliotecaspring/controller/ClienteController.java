package br.com.leonardo.bibliotecaspring.controller;


import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO>encontrarPeloId(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(this.clienteService.localizarPeloId(id));
    }


    @GetMapping("/localizarTodos") // Ainda não usarei o pageable
    public ResponseEntity<Iterable<ClienteDTO>> localizarTodos(){
        return ResponseEntity.ok().body(this.clienteService.localizarTodos());
    }

    @PostMapping("/cadastrarCliente")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDTO clienteDTO ){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        this.clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto){
        return ResponseEntity.ok().body(this.clienteService.editarCliente(id, clienteDto));
    }
}
