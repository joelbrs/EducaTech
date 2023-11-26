package br.com.educatech.EducaTech.dtos.material;

public class MaterialDTOIn {

    private String arquivo;
    private String nome;

    public MaterialDTOIn() {}

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
