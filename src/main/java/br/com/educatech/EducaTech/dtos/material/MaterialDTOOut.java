package br.com.educatech.EducaTech.dtos.material;

public class MaterialDTOOut {

    private Long id;
    private String arquivo;
    private String nome;

    public MaterialDTOOut() {}

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
}
