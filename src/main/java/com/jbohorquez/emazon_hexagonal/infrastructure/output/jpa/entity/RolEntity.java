package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @OneToMany(mappedBy = "rol")
    private List<UserEntity> users;
}
