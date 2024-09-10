package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import com.jbohorquez.emazon_hexagonal.application.validation.Adult;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Entity
@Table(name = USERS)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = LAST_NAME_REQUIRED)
    private String lastName;

    @NotNull(message = ID_DOCUMENT_REQUIRED)
    @Digits(integer = MAX_DOCUMENT, fraction = ZERO, message = ID_DOCUMENT_NUMERIC)
    private Long identityDocument;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = PHONE_NUMBER, message = PHONE_INVALID)
    private String phone;

    @NotNull(message = BIRTHDATE_REQUIRED)
    @Adult(message = USER_MUST_BE_ADULT)
    private LocalDate birthdate;

    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID_FORMAT)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;


    @ManyToOne
    @JoinColumn(name = ROL_ID_LIST)
    private RolEntity rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE + rol.getName()));
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
