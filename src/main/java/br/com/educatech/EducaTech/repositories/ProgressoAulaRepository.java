package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.ProgressoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Camada de Acesso ao Banco de Dados da Entidade ProgressoAula, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 *
 * A interface JpaRepository não oferece métodos específicos que foram utilizados no desenvolvimento do sistema,
 * portanto, utilizando a sintaxe JPQL, uma linguagem de consulta orientada a objetos similar ao próprio SQL, alguns
 * métodos foram criados para suprir essa necessidade
 * */
@Repository
public interface ProgressoAulaRepository extends JpaRepository<ProgressoAula, Long> {

    /**
     * Método que busca o Progresso de uma Aula por Usuário
     * */
    @Query("select p from ProgressoAula p where p.aula.id = :idAula and p.usuario.id = :idUsuario ")
    Optional<ProgressoAula> findByIdAulaAndIdUsuario(Long idAula, Long idUsuario);
}
