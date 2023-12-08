package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Camada de Acesso ao Banco de Dados da Entidade Módulo, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 *
 * A interface JpaRepository não oferece métodos específicos que foram utilizados no desenvolvimento do sistema,
 * portanto, utilizando a sintaxe JPQL, uma linguagem de consulta orientada a objetos similar ao próprio SQL, alguns
 * métodos foram criados para suprir essa necessidade
 * */
@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    /**
     * Método que busca o Módulo com a maior ordem cadastrada
     * */
    @Query("SELECT m FROM Modulo m WHERE m.curso.id = :idCurso and m.ordem = (SELECT MAX(m2.ordem) FROM Modulo m2 where m2.curso.id = :idCurso)")
    Optional<Modulo> findModuleWithMaxOrder(Long idCurso);

    /**
     * Método que busca todos os Módulos de um determinado Curso
     * */
    @Query("SELECT m FROM Modulo m WHERE m.curso.id = :id")
    List<Modulo> findAllByIdCourse(Long id);
}
