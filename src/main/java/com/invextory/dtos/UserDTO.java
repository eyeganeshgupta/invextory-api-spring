package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
import com.invextory.constants.RegexPattern;
import com.invextory.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = AppText.NAME_REQUIRED)
    @Size(max = 100, message = AppText.NAME_TOO_LONG)
    private String name;

    @NotBlank(message = AppText.EMAIL_REQUIRED)
    @Email(message = AppText.EMAIL_INVALID)
    @Size(max = 100, message = AppText.EMAIL_TOO_LONG)
    private String email;

    @JsonIgnore
    @NotBlank(message = AppText.PASSWORD_REQUIRED)
    @Size(min = 8, max = 100, message = AppText.PASSWORD_LENGTH)
    @Pattern(regexp = RegexPattern.PASSWORD_COMPLEX, message = AppText.PASSWORD_COMPLEXITY)
    private String password;

    @Pattern(regexp = RegexPattern.PHONE_NUMBER, message = AppText.PHONE_INVALID)
    private String phoneNumber;

    @NotNull(message = AppText.ROLE_REQUIRED)
    private UserRole role;

    private List<TransactionDTO> transactions;

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
