package br.com.educatech.EducaTech.enums;


/**
 * Enumeração para determinar o Status de um Curso, podendo ser INCOMPLETO ou COMPLETO
 * */
public enum StatusCurso {
    INCOMPLETO(1L),
    COMPLETO(2L);

    private final Long code;

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
