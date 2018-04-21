package br.com.cursomc.resources.exception;

import br.com.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //customizar o documento JSON para retornar para um determinado controlador e / ou tipo de exceção
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) // Para informar que esta exceção refere-se a classe ObjectNotFoundException
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
