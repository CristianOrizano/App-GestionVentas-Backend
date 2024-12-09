package com.gestionventas.shared.exeption;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //exeption personalizado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> ResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        int status = HttpStatus.NOT_FOUND.value(); // Código HTTP 404
        String error = HttpStatus.NOT_FOUND.getReasonPhrase(); // "Not Found"
        String message = exception.getMessage(); // Mensaje definido en la excepción
        String path = webRequest.getDescription(false).split("=")[1]; // Extrae la ruta de la solicitud

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }

    // para manejar cualquier excepción general en tu aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
        System.out.println("entro aqui exception global ===>");
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String message = exception.getMessage();
        String path = webRequest.getDescription(false).split("=")[1];

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //manejar los errores en validacion de las apis
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError)error).getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    //para la validacion de parametros de ruta
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetalles> manejarConstraintViolationException(ConstraintViolationException exception, WebRequest webRequest) {
        int status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();

        // Mensajes de violaciones de restricciones
        List<String> messages = exception.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
        String message = String.join(", ", messages); // Combina todos los mensajes de validación
        String path = webRequest.getDescription(false).split("=")[1];

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }


}
