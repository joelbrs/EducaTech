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


/**
 * Camada de Acesso ao Banco de Dados da Entidade Aula, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 *
 * A interface JpaRepository não oferece métodos específicos que foram utilizados no desenvolvimento do sistema,
 * portanto, utilizando a sintaxe JPQL, uma linguagem de consulta orientada a objetos similar ao próprio SQL, alguns
 * métodos foram criados para suprir essa necessidade
 * */
@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    /**
     * Método que busca a aula com a maior ordem cadastrada
     * */
    @Query("SELECT a FROM Aula a WHERE a.curso.id = :idCurso and a.modulo.id = :idModulo and a.ordem = (SELECT MAX(a2.ordem) FROM Aula a2 where a2.curso.id = :idCurso and a2.modulo.id = :idModulo)")
    Optional<Aula> findAulaWithMaxOrder(Long idCurso, Long idModulo);

    /**
     * Método que todas as aulas de um específico Curso e Módulo
     * */
    @Query("SELECT a FROM Aula a WHERE a.curso.id = :idCurso AND a.modulo.id = :idModulo")
    List<Aula> findAllByCourseAndModule(Long idCurso, Long idModulo);

    /**
     * Método que busca todas as aulas por Módulo
     * */
    @Query("SELECT a FROM Aula a WHERE a.modulo.id = :id")
    List<Aula> findAllByModule(Long id);
}
