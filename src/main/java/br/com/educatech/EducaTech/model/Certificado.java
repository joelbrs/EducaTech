package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Certificado, referenciada pela tabela "tb_certificado"
 * */
@Entity
@Table(name = "tb_certificado")
public class Certificado {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String arquivo;

    /**
     * Relacionamento "um-para-muitos" com a entidade Curso, onde o mapeamento desse relacionamento foi realizado
     * na entidade Curso
     * */
    @OneToMany(mappedBy = "modeloCertificado")
    private Set<Curso> cursos = new HashSet<>();


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public Certificado() {}

    public Certificado(String nome, String arquivo, Curso curso) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.cursos.add(curso);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Set<Curso> getCursos() {
        return Collections.unmodifiableSet(cursos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificado that = (Certificado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
