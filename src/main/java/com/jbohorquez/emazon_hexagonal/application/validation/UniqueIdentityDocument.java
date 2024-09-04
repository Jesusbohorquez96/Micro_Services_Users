package com.jbohorquez.emazon_hexagonal.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueIdentityDocumentValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIdentityDocument {
    String message() default "Identity document already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
