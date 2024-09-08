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
    private Rol rol;

    public User(Long id, String name, String lastName, Long identityDocument, String phone, LocalDate birthdate, String email, String password, Rol rol) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public User(String s, String mail, String number, String doe) {
    }

    public User() {

    }

    public User(long l, String john, String doe, String number, String mail, String password, Object o) {
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
