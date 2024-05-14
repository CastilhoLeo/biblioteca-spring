package br.com.leonardo.bibliotecaspring.controller;


import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO>encontrarPeloId(@PathVariable Long id){
        return ok()
                .body(this.clienteService.localizarPeloId(id));
    }

    @GetMapping
        public ResponseEntity<Page<ClienteDTO>> pesquisaPaginada(
                @RequestParam(value = "nome", required = false) String nome,
                @RequestParam(value = "cpf", required = false) String cpf, Pageable pageable){
            return ok().body(this.clienteService.pesquisaDinamica(nome, cpf, pageable));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> cadastrarCliente( @RequestBody ClienteDTO clienteDTO ){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        this.clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto){
        return ok().body(this.clienteService.editarCliente(id, clienteDto));
    }
}
