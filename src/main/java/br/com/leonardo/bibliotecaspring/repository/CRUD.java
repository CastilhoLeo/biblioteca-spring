package br.com.leonardo.bibliotecaspring.repository;

import org.springframework.data.repository.CrudRepository;

public interface CRUD<T, id>  extends CrudRepository<T, id> {

}
