package com.invextory.dtos.request;

import com.invextory.constants.AppText;
import com.invextory.constants.RegexPattern;
import com.invextory.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = AppText.NAME_REQUIRED)
    @Size(max = 100, message = AppText.NAME_TOO_LONG)
    private String name;

    @NotBlank(message = AppText.EMAIL_REQUIRED)
    @Email(message = AppText.EMAIL_INVALID)
    @Size(max = 100, message = AppText.EMAIL_TOO_LONG)
    private String email;

    @NotBlank(message = AppText.PASSWORD_REQUIRED)
    @Size(min = 8, max = 100, message = AppText.PASSWORD_LENGTH)
    @Pattern(regexp = RegexPattern.PASSWORD_COMPLEX, message = AppText.PASSWORD_COMPLEXITY)
    private String password;

    @NotNull(message = AppText.ROLE_REQUIRED)
    private UserRole role;
}
