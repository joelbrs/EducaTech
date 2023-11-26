package br.com.educatech.EducaTech.dtos.modulo;

import br.com.educatech.EducaTech.dtos.material.MaterialDTOIn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModuloDTOIn {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer ordem;

    @NotNull
    private Long curso;

    private MaterialDTOIn material;

    public ModuloDTOIn() {}

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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Long getCurso() {
        return curso;
    }

    public void setCurso(Long curso) {
        this.curso = curso;
    }

    public MaterialDTOIn getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTOIn material) {
        this.material = material;
    }
}
