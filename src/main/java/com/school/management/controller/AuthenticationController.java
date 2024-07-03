package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.LoginUserDto;
import com.school.management.dto.RefreshTokenDto;
import com.school.management.dto.RegisterUserDto;
import com.school.management.entities.User;
import com.school.management.request.LoginResponse;
import com.school.management.request.RefreshTokenResponse;
import com.school.management.service.AuthenticationService;
import com.school.management.service.JwtService;
import com.school.management.service.UserService;



@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin-signup")
    public User adminSignUp(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto,1L);
    }

    @PostMapping("/tutor-signup")
    public User studentSignUp(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto,2L);
    }
    
    @PostMapping("/student-signup")
    public User tutorSignUp(@RequestBody RegisterUserDto registerUserDto) {
        return  authenticationService.signup(registerUserDto,3L);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) throws Exception {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
            .expiresIn(jwtService.getExpirationTime())
            .token(jwtToken)
            .refreshToken(refreshToken)
            .build();

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        User user = userService.findByEmail(jwtService.extractUsername(refreshTokenDto.getToken()));
        String jwtToken = jwtService.generateToken(user);

        RefreshTokenResponse loginResponse = RefreshTokenResponse.builder()
            .accessToken(jwtToken)
            .build();
        return ResponseEntity.ok(loginResponse);
    }
}