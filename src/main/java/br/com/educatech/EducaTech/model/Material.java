package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Entidade Aula, referenciada pela tabela "tb_material"
 * */
@Entity
@Table(name = "tb_material")
public class Material {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String arquivo;
    private String nome;

    /**
     * Relacionamento "um-para-muitos" com a entidade de Módulo, onde o mapeamento desse relacionamento foi realizado
     * na entidade Módulo
     * */
    @OneToMany(mappedBy = "material")
    private Set<Modulo> modulos;

    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public Material() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Modulo> getModulos() {
        return Collections.unmodifiableSet(modulos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
