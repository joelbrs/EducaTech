package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Aula;
import br.com.educatech.EducaTech.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    @Query("SELECT a FROM Aula a")
    Page<Aula> findAllPaged(String titulo, Pageable pageable);

    @Query("SELECT a FROM Aula a WHERE a.ordem = (SELECT MAX(a2.ordem) FROM Aula a2)")
    Optional<Aula> findAulaWithMaxOrder();

    @Query("SELECT a FROM Aula a WHERE a.id.curso.id = :idCurso AND a.id.modulo.id = :idModulo")
    List<Aula> findAllByCourseAndModule(Long idCurso, Long idModulo);
}
