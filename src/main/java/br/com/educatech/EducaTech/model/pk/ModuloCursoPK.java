package br.com.educatech.EducaTech.model.pk;

import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.Modulo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ModuloCursoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    public ModuloCursoPK() {}

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuloCursoPK that = (ModuloCursoPK) o;
        return Objects.equals(curso, that.curso) && Objects.equals(modulo, that.modulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, modulo);
    }
}
