package com.jbohorquez.emazon_hexagonal.application.validation;

import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueIdentityDocumentValidator implements ConstraintValidator<UniqueIdentityDocument, Long> {

    private final UserPersistencePort userPersistencePort;

    public UniqueIdentityDocumentValidator(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public boolean isValid(Long identityDocument, ConstraintValidatorContext context) {
        if (identityDocument == null) {
            return true;
        }
        return !userPersistencePort.existsByIdentityDocument(identityDocument);
    }
}

