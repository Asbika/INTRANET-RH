package com.giantLink.RH.models.response;

import java.util.Map;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {//Classe personnalisée pour représenter les détails d'une erreur de validation 
    private int errorCode;
    private String errorMessage;
    private Map<String, String> validationErrors;


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}