package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Camada de Acesso ao Banco de Dados da Entidade Material, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 * */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
