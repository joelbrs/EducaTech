package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    @Query("SELECT m FROM Modulo m")
    Page<Modulo> findAllPaged(String titulo, Pageable pageable);

    @Query("SELECT m FROM Modulo m WHERE m.ordem = (SELECT MAX(m2.ordem) FROM Modulo m2)")
    Optional<Modulo> findModuleWithMaxOrder();

    @Query("SELECT m FROM Modulo m WHERE m.curso.id = :id")
    List<Modulo> findAllByIdCourse(Long id);
}
