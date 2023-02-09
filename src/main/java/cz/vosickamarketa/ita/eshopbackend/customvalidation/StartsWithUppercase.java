package cz.vosickamarketa.ita.eshopbackend.customvalidation;

import cz.vosickamarketa.ita.eshopbackend.customvalidation.validator.StartsWithUppercaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartsWithUppercaseValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWithUppercase {

    String message() default "must start with uppercase";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
