package com.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid name: Only letters and spaces are allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
