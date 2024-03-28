package br.com.leonardo.bibliotecaspring.controller;


import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public void cadastrarCliente(Cliente cliente){

        clienteService.cadastrarCliente(cliente);
    }

    @DeleteMapping("/deletarCliente/{id}")
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

    @PutMapping("/editarCliente")
    public ResponseEntity<Cliente> editarCliente(Long id, Cliente cliente){
        return ResponseEntity.ok().body(this.clienteService.editarCliente(id, cliente));
    }
}
