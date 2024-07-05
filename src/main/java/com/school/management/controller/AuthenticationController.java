package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return this.authenticationService.signup(registerUserDto,1L);
    }

    @PostMapping("/tutor-signup")
    public User studentSignUp(@RequestBody RegisterUserDto registerUserDto) {
        return this.authenticationService.signup(registerUserDto,2L);
    }
    
    @PostMapping("/student-signup")
    public User tutorSignUp(@RequestBody RegisterUserDto registerUserDto) {
        return  this.authenticationService.signup(registerUserDto,3L);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody LoginUserDto loginUserDto) throws Exception {
        final User authenticatedUser = authenticationService.authenticate(loginUserDto);

        final String jwtToken = jwtService.generateToken(authenticatedUser);
        final String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        final LoginResponse loginResponse = LoginResponse.builder()
            .token(jwtToken)
            .refreshToken(refreshToken)
            .build();

        return loginResponse;
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        final User user = userService.findByEmail(jwtService.extractUsername(refreshTokenDto.getToken()));
        final String jwtToken = jwtService.generateToken(user);

        final RefreshTokenResponse loginResponse = RefreshTokenResponse.builder()
            .accessToken(jwtToken)
            .build();
        return loginResponse;
    }
}