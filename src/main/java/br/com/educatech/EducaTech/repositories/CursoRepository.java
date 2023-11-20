package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select c from Curso c where ( :titulo is null or lower(c.titulo) like lower(concat('%', :titulo, '%')) ) ")
    Page<Curso> findAllPaged(String titulo, Pageable pageable);
}
