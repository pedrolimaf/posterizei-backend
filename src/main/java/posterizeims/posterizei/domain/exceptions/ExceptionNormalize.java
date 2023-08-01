package posterizeims.posterizei.domain.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionNormalize {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(EntityNotFoundException ex){
        var errors = ex.getMessage();
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentNotValid(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(argumentNotValidData::new).toList());
    }

    public record argumentNotValidData(
        String validation,
        String message
    ){
        public argumentNotValidData(FieldError error){
            this(error.getField().toUpperCase(),error.getDefaultMessage().toUpperCase());
        }
    }

}
