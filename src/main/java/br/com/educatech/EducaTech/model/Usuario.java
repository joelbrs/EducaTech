package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.TipoUsuarioEnum;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Entidade Usuário, referenciada pela tabela "tb_usuario"
 * */
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */

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

    /**
     * Relacionamento "um-para-muitos" com a entidade de ProgressoCurso, onde o mapeamento desse relacionamento foi realizado
     * na entidade ProgressoCurso
     * */
    @OneToMany(mappedBy = "usuario")
    private Set<ProgressoCurso> progressoCurso = new HashSet<>();

    /**
     * Relacionamento "um-para-muitos" com a entidade de ProgressoAula, onde o mapeamento desse relacionamento foi realizado
     * na entidade ProgressoAula
     * */
    @OneToMany(mappedBy = "usuario")
    private Set<ProgressoAula> progressoAula = new HashSet<>();


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

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
