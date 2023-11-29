package br.com.educatech.EducaTech.dtos.curso.certificado;

public class ModeloCertificadoRequestDTO {
    private Long idCurso;
    private Long idUsuario;

    public ModeloCertificadoRequestDTO() {}

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
