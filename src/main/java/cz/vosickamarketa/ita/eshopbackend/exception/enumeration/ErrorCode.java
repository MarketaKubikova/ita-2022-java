package cz.vosickamarketa.ita.eshopbackend.exception.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN_EXCEPTION("0000"),
    PRODUCT_NOT_FOUND("0001"),
    INVALID_ARGUMENT("0002");

    private final String code;
}
