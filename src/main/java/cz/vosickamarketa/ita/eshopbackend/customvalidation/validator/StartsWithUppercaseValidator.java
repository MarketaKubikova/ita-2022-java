package cz.vosickamarketa.ita.eshopbackend.customvalidation.validator;

import cz.vosickamarketa.ita.eshopbackend.customvalidation.StartsWithUppercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithUppercaseValidator implements ConstraintValidator<StartsWithUppercase, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return s.matches("^[A-Z].+");
    }
}
