package br.com.educatech.EducaTech.repositories;

import br.com.educatech.EducaTech.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u")
    Page<Usuario> findAllPaged(String cpf, String nome, Pageable pageable);
}
