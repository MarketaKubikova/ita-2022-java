package cz.vosickamarketa.ita.eshopbackend.exception;

import cz.vosickamarketa.ita.eshopbackend.exception.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ItaException {
    public ProductNotFoundException(Long id) {
        super("Product %d has not been found.".formatted(id),
                ErrorCode.PRODUCT_NOT_FOUND.getCode(),
                HttpStatus.NOT_FOUND);
    }
}
