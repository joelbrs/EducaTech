package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal cargaHoraria;
    private String imagem;

    @OneToMany(mappedBy = "id.curso")
    private Set<Aula> aulas = new HashSet<>();

    @OneToMany(mappedBy = "curso")
    private Set<Modulo> modulos = new HashSet<>();
;
    public Curso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(BigDecimal cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Set<Modulo> getModulos() {
        return Collections.unmodifiableSet(modulos);
    }

    public Set<Aula> getAulas() {
        return Collections.unmodifiableSet(aulas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
