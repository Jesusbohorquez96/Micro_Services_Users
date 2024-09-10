package com.jbohorquez.emazon_hexagonal.constants;

public class ValidationConstants {

    public static final long TIME = 1000 * 60 * 60 * 24;
    public static final long BUILDER_ID = 1l;

    public static final int ZERO = 0;
    public static final int DECIMALS = 2;
    public static final int SEVEN = 7;
    public static final int INTEGERS = 10;
    public static final int MAYOR = 18;
    public static final int MAX_DOCUMENT = 20;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;

    public static final String PAGE = "0";
    public static final String SIZE = "10";
    public static final String ID = "id";
    public static final String ROL = "rol";
    public static final String TO_ROL = "toRol";
    public static final String ROLES = "roles";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String PHONE_NUMBER = "^\\+?[0-9]{1,13}$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    public static final String NAME = "name";
    public static final String AUX = "aux_bodega";
    public static final String CUSTOMER = "customer";
    public static final String ADMIN = "admin";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";

    public static final String MESSAGE = "message";
    public static final String SPRING = "spring";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_INVALID = "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID_FORMAT = "Email must have a valid format";
    public static final String ID_DOCUMENT_REQUIRED = "Identity document is required";
    public static final String ID_DOCUMENT_NUMERIC = "Identity document must be numeric and cannot contain decimals";
    public static final String PHONE_REQUIRED = "Phone number is required";
    public static final String PHONE_INVALID = "Phone number must be a maximum of 13 characters and may include the '+' symbol";
    public static final String BIRTHDATE_REQUIRED = "Birthdate is required";
    public static final String BIRTHDATE_PAST = "Birthdate must be in the past";
    public static final String ROL_REQUIRED = "Rol is required";
    public static final String NAME_LONG = "Name is too long";
    public static final String DESCRIPTION_LONG = "Description is too long";
    public static final String DESCRIPTION_REQUIRED = "Description cannot be blank";
    public static final String DESCRIPTION_MAX_LENGTH_EXCEEDED = "The description must not exceed 90 characters";
    public static final String USER_MUST_BE_ADULT = "User must be at least 18 years old";
    public static final String USER_NOT_FOUND = "User must be an adult";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public static final String USER_ID_TARGET = "userId";
    public static final String USER_NAME_TARGET = "userName";
    public static final String USER_LAST_NAME_TARGET = "userLastName";
    public static final String USER_IDENTITY_DOCUMENT_TARGET = "userIdentityDocument";
    public static final String USER_PHONE_TARGET = "userPhone";
    public static final String USER_BIRTHDATE_TARGET = "userBirthdate";
    public static final String USER_EMAIL_TARGET = "userEmail";
    public static final String USER_PASSWORD_TARGET = "userPassword";
    public static final String USER_ROL_TARGET = "userRol";
    public static final String LAST_NAME = "lastName";
    public static final String IDENTITY_DOCUMENT= "identityDocument";
    public static final String PHONE = "phone";
    public static final String BIRTHDATE = "birthdate";
    public static final String EMAIL = "email";
    public static final String PASSWORD_SOURCE= "password";
    public static final String DESCRIPTION = "description";
    public static final String ROL_ID_TARGET = "rolId";
    public static final String ROL_ID_LIST = "rol_id";
    public static final String USERS = "users";
    public static final String ROLE =  "ROLE_";
    public static final String ROL_NAME_TARGET = "rolName";
    public static final String ROL_DESCRIPTION_TARGET = "rolDescription";

    public static final String TITLE = "Hexagonal Monolithic Category API";
    public static final String TERMS_OF_SERVICE_URL = "http://swagger.io/terms/";
    public static final String LICENSE_NAME = "Apache 2.0";
    public static final String LICENSE_URL = "http://springdoc.org";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL = "/**";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
