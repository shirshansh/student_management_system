package com.studentmanagement.controller;

import com.studentmanagement.dto.AuthenticationResponseDto;
import com.studentmanagement.dto.LoginRequestDto;
import com.studentmanagement.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Auth Controller",
        description = "Authentication APIs"
)
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login with credentials",
            description = "Returns the json web token (jwt)"
    )
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto request) {

        AuthenticationResponseDto response = authenticationService.authenticate(request);

        return ResponseEntity.ok(response);
    }
}
