package br.com.educatech.EducaTech.enums;

/**
 * Enumeração para determinar o Tipo de Usuário, podendo ser ALUNO ou ADMIN
 * */
public enum TipoUsuarioEnum {
    ALUNO(1L),
    ADMIN(2L);

    private final Long code;

    TipoUsuarioEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public static TipoUsuarioEnum valueOf(Long code) throws Exception {
        for (TipoUsuarioEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        throw new Exception("Tipo Usuário Informado Não Existe!");
    }
}
