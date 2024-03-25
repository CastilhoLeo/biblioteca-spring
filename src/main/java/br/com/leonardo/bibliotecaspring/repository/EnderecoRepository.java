package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
}
