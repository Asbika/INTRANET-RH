package com.giantLink.RH.exceptions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.giantLink.RH.models.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationException extends RuntimeException implements AuthenticationEntryPoint {
    private final MessageSource messageSource;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = messageSource.getMessage("unauthorized.exception", null, "No MESSAGE", LocaleContextHolder.getLocale());
        ErrorResponse errorDetails = ErrorResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(authException.getMessage())
                .errorDetails(message)
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        objectMapper.writeValue(response.getOutputStream(),errorDetails);
    }
}

