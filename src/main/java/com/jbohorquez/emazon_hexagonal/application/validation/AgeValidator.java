package com.jbohorquez.emazon_hexagonal.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<Adult, LocalDate> {

    private int age;

    @Override
    public void initialize(Adult adult) {
        this.age = adult.age();
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return false;
        }
        return Period.between(birthdate, LocalDate.now()).getYears() >= age;
    }
}
