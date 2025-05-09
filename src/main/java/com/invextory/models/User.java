package com.invextory.models;

import com.invextory.constants.AppText;
import com.invextory.constants.RegexPattern;
import com.invextory.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppText.NAME_REQUIRED)
    @Size(max = 100, message = AppText.NAME_TOO_LONG)
    private String name;

    @NotBlank(message = AppText.EMAIL_REQUIRED)
    @Email(message = AppText.EMAIL_INVALID)
    @Size(max = 100, message = AppText.EMAIL_TOO_LONG)
    @Column(unique = true)
    private String email;

    @Pattern(
            regexp = RegexPattern.PASSWORD_COMPLEX,
            message = AppText.PASSWORD_COMPLEXITY
    )
    @NotBlank(message = AppText.PASSWORD_REQUIRED)
    @Size(min = 8, max = 100, message = AppText.PASSWORD_LENGTH)
    private String password;

    @Pattern(regexp = RegexPattern.PHONE_NUMBER, message = AppText.PHONE_INVALID)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = AppText.ROLE_REQUIRED)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // @OneToMany(mappedBy = "user")
    // private List<Transaction> transactions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "User{" +
                "createdAt=" + createdAt +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
