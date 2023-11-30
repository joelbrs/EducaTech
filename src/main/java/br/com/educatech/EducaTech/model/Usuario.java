package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.TipoUsuarioEnum;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;
    private Long tipoUsuario;

    @OneToMany(mappedBy = "usuario")
    private Set<ProgressoCurso> progressoCurso = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<ProgressoAula> progressoAula = new HashSet<>();

    public Usuario() {}

    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTipoUsuario() throws Exception {
        return TipoUsuarioEnum.valueOf(tipoUsuario);
    }

    public Set<ProgressoCurso> getProgressoCurso() {
        return progressoCurso;
    }

    public void setProgressoCurso(Set<ProgressoCurso> progressoCurso) {
        this.progressoCurso = progressoCurso;
    }

    @PrePersist
    public void setTipoUsuario() {
        this.tipoUsuario = TipoUsuarioEnum.ALUNO.getCode();
    }

    public Set<ProgressoAula> getProgressoAula() {
        return Collections.unmodifiableSet(progressoAula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
