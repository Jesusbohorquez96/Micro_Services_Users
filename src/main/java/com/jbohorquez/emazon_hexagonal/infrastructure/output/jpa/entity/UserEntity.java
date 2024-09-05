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
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Identity document is required")
    @Digits(integer = MAX_DOCUMENT, fraction = ZERO, message = "Identity document must be numeric and cannot contain decimals")
    private Long identityDocument;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = PHONE_NUMBER, message = "Phone number must be a maximum of MAX_PHONE characters and may include the '+' symbol")
    private String phone;

    @NotNull(message = "Birthdate is required")
    @Adult(message = "User must be at least 18 years old")
    private LocalDate birthdate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must have a valid format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
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
