package com.jbohorquez.emazon_hexagonal.domain.model;

import java.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private String lastName;
    private Long identityDocument;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String rol;

    public User(String password, String email, LocalDate birthdate, String phone, Long identityDocument, String lastName, String name, Long id, String rol) {
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.identityDocument = identityDocument;
        this.lastName = lastName;
        this.name = name;
        this.id = id;
        this.rol = rol;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(Long identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
