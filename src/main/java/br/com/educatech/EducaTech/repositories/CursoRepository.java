package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Camada de Acesso ao Banco de Dados da Entidade Curso, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 * */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
