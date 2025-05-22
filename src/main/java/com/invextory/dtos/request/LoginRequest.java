package com.invextory.dtos.request;

import com.invextory.constants.AppText;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = AppText.LOGIN_EMAIL_REQUIRED)
    @Email(message = AppText.EMAIL_INVALID)
    private String email;

    @NotBlank(message = AppText.LOGIN_PASSWORD_REQUIRED)
    private String password;

}
