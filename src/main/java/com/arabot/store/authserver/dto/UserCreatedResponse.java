package com.arabot.store.authserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreatedResponse {

    private String email;

    private String firstName;

}
