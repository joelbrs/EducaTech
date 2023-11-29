package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.ProgressoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgressoAulaRepository extends JpaRepository<ProgressoAula, Long> {

    @Query("select p from ProgressoAula p where p.aula.id = :idAula and p.usuario.id = :idUsuario ")
    Optional<ProgressoAula> findByIdAulaAndIdUsuario(Long idAula, Long idUsuario);
}
