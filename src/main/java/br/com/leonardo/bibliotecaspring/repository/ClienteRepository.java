package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
