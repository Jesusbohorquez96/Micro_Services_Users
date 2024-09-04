package com.jbohorquez.emazon_hexagonal.enums;

import lombok.Getter;

@Getter
public enum SortByFieldsArticles {

    NAME("name"),
    BRAND("brand.name"),
    CATEGORY("categories.name");

    private final String value;

    SortByFieldsArticles(String value) {
        this.value = value;
    }

}
