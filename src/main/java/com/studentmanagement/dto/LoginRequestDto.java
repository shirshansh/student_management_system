package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login Request")
public class LoginRequestDto {

    @Schema(
            description = "Username",
            example = "admin"
    )
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(
            description = "Password",
            example = "admin123"
    )
    @NotBlank(message = "Password is required")
    private String password;

    public LoginRequestDto() {

    }

    public LoginRequestDto(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {

        return "LoginRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
