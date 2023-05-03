package pe.martinsam.catalogproducts.application.exception;

import jakarta.validation.ValidationException;

public class ProductException extends ValidationException {

        public ProductException(String message) {
            super(message);
        }
}
