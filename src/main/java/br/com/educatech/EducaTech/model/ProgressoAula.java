package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_progresso_aula")
public class ProgressoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
    private Boolean assistida;

    public ProgressoAula() {}

    public ProgressoAula(Long id, Usuario usuario, Aula aula, Boolean assistida) {
        this.id = id;
        this.usuario = usuario;
        this.aula = aula;
        this.assistida = assistida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Boolean getAssistida() {
        return assistida;
    }

    public void setAssistida(Boolean assistida) {
        this.assistida = assistida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressoAula that = (ProgressoAula) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
