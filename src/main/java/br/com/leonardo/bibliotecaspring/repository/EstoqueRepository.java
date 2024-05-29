package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
