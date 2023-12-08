package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Camada de Acesso ao Banco de Dados da Entidade Usuário, sendo, portanto, uma interface que extende de JpaRepository que
 * fornece alguns métodos "base" para consultas, inserções e deleções na tabela referenciada pela Entidade
 *
 * A interface JpaRepository não oferece métodos específicos que foram utilizados no desenvolvimento do sistema,
 * portanto, utilizando a sintaxe JPQL, uma linguagem de consulta orientada a objetos similar ao próprio SQL, alguns
 * métodos foram criados para suprir essa necessidade
 * */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método que busca um Usuário a partir de seu e-mails
     * */
    @Query("select u from Usuario u where u.email = :email")
    Optional<Usuario> findByEmail(String email);
}
