package br.com.leonardo.bibliotecaspring.repository;

import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM livro WHERE l.titulo ILIKE %:titulo% AND l.autor ILIKE %:autor:")
    Page<Livro> pesquisaDinamica (String titulo, String autor, Pageable pageable);

}
