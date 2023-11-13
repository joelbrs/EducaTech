package br.com.educatech.EducaTech.dtos.usuario;

import br.com.educatech.EducaTech.enums.TipoUsuarioEnum;

public class UsuarioDTOOut {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private Long tipoUsuario;

    public UsuarioDTOOut() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuarioEnum getTipoUsuario() throws Exception {
        return TipoUsuarioEnum.valueOf(tipoUsuario);
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        if (tipoUsuario != null) {
            this.tipoUsuario = tipoUsuario.getCode();
        }
    }
}
