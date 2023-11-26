package br.com.educatech.EducaTech.enums;

public enum StatusCurso {
    INCOMPLETO(1L),
    COMPLETO(2L);

    private Long code;

    StatusCurso(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public static StatusCurso valueOf(Long code) throws Exception {
        for (StatusCurso t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        throw new Exception("Status Informado Não Existe!");
    }
}
