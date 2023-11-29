package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.ProgressoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressoCursoRepository extends JpaRepository<ProgressoCurso, Long> {

    @Query("select p from ProgressoCurso p where p.id.curso.id = :idCurso and p.id.usuario.id = :idUsuario")
    ProgressoCurso findByIdCourseAndIdUser(Long idCurso, Long idUsuario);
}
