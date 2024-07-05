package com.school.management.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}