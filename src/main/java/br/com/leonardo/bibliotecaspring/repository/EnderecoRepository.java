package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
}
