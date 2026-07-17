package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication Response")
public class AuthenticationResponseDto {

    @Schema(description = "Json Web Token")
    private String token;

    public AuthenticationResponseDto() {

    }

    public AuthenticationResponseDto(String token) {

        this.token = token;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    @Override
    public String toString() {

        return "AuthenticationResponseDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
