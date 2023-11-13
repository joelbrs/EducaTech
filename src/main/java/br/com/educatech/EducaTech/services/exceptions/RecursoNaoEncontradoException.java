package br.com.educatech.EducaTech.services.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String msg) {
        super(msg);
    }

    public RecursoNaoEncontradoException(Long id) {
        super("Recurso não encontrado, id: " + id);
    }
}
