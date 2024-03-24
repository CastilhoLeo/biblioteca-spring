package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
