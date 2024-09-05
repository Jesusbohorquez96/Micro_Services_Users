package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@Entity
@Table(name = "rol")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Not null max NAME_MAX_LENGTH characters
    @Column(length= NAME_MAX_LENGTH, nullable = false)
    @Size(message = "Name is too long", max = NAME_MAX_LENGTH)
    private String name;

    //Not null max DESCRIPTION_MAX_LENGTH characters
    @Column(length = DESCRIPTION_MAX_LENGTH, nullable = false)
    @Size(message = "Description is too long", max = DESCRIPTION_MAX_LENGTH)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
