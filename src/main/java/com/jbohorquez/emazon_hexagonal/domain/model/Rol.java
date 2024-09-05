package com.jbohorquez.emazon_hexagonal.domain.model;

public class Rol {

    private Long id;
    private String name;
    private String description;

    public Rol(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
