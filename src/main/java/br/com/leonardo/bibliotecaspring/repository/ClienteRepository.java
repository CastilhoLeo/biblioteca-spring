package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
