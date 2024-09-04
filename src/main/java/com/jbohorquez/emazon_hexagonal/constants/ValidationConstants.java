package com.jbohorquez.emazon_hexagonal.constants;

public class ValidationConstants {

    public static final int ZERO = 0;
    public static final int ASSOCIATEDONE = 1;
    public static final int DECIMALS = 2;
    public static final int ASSOCIATED = 3;
    public static final int INTEGERS = 10;
    public static final int MAX_PHONE = 13;
    public static final int MAX_DOCUMENT = 20;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int DESCRIPTION_BRAND_MAX_LENGTH = 120;

    public static final String PAGE = "0";
    public static final String SIZE = "10";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String PHONE_NUMBER = "^\\+?[0-9]{1,13}$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    public static final String NAME = "name";
    public static final String AUX = "aux_bodega";
    public static final String CUSTOMER = "Customer";


    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
