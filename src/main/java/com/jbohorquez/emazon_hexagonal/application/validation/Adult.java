package com.jbohorquez.emazon_hexagonal.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    String message() default "User must be an adult";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int age() default 18;
}
