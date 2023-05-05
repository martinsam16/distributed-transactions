package pe.martinsam.catalogproducts.infraestructure.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.martinsam.catalogproducts.application.exception.ProductException;
import pe.martinsam.catalogproducts.common.infraestructure.rest.handler.dto.ErrorRestHandlerDto;

import java.time.LocalDateTime;


@ControllerAdvice
public class ProductExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ProductException.class
    })
    protected ResponseEntity<Object> handleConflictProduct(RuntimeException ex, WebRequest request) {
        ErrorRestHandlerDto body = new ErrorRestHandlerDto(
                LocalDateTime.now().toString(),
                HttpStatus.CONFLICT.value(),
                ex.getLocalizedMessage(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(body, null, HttpStatus.CONFLICT);
    }

}
