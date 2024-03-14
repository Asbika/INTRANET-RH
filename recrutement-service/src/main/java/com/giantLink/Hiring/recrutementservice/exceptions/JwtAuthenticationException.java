package com.giantLink.Hiring.recrutementservice.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giantLink.Hiring.recrutementservice.models.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(authException.getMessage())
                .description("Unauthorized")
                .timestamp(new Date())
                .build();

        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
