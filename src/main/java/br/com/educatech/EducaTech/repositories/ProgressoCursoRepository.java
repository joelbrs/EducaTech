package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.ProgressoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Camada de Acesso ao Banco de Dados da Entidade ProgressoCurso, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 *
 * A interface JpaRepository não oferece métodos específicos que foram utilizados no desenvolvimento do sistema,
 * portanto, utilizando a sintaxe JPQL, uma linguagem de consulta orientada a objetos similar ao próprio SQL, alguns
 * métodos foram criados para suprir essa necessidade
 * */
@Repository
public interface ProgressoCursoRepository extends JpaRepository<ProgressoCurso, Long> {

    /**
     * Método que busca o Progresso de um Curso por Usuário
     * */
    @Query("select p from ProgressoCurso p where p.curso.id = :idCurso and p.usuario.id = :idUsuario")
    Optional<ProgressoCurso> findByIdCourseAndIdUser(Long idCurso, Long idUsuario);
}
