package com.arabot.store.authserver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @Size(min = 6, max = 16)
    private String password;

}
