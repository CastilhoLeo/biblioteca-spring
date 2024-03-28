package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
