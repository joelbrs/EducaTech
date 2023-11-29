package br.com.educatech.EducaTech.dtos.curso.certificado;

public class ModeloCertificadoDTO {

    private Long id;
    private String nome;
    private String arquivo;

    public ModeloCertificadoDTO() {}

    public ModeloCertificadoDTO(String nome, String arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
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
}
