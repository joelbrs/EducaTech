package br.com.educatech.EducaTech.controllers.exceptions;

import br.com.educatech.EducaTech.exceptions.RecursoExceptionHandler;
import br.com.educatech.EducaTech.exceptions.StandardError;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RecursosExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> recursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Recurso não encontrado!";
        String path = request.getRequestURI();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(),  error, e.getMessage(), path));
    }
}
