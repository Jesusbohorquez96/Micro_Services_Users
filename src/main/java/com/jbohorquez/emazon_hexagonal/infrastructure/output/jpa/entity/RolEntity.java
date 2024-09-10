package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Entity
@Table(name = ROLES)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    @Size(message = NAME_LONG, max = NAME_MAX_LENGTH)
    private String name;

    @Column(length = DESCRIPTION_MAX_LENGTH, nullable = false)
    @Size(message = DESCRIPTION_LONG, max = DESCRIPTION_MAX_LENGTH)
    private String description;

    @OneToMany(mappedBy = ROL)
    private List<UserEntity> users;

    public RolEntity(long l, String roleUser, String userRoleDescription) {
    }
}
