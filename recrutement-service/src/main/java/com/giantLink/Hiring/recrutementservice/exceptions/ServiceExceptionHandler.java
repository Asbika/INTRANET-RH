package com.giantLink.Hiring.recrutementservice.exceptions;

import com.giantLink.Hiring.recrutementservice.models.response.ValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<Object> handleResourceAlreadyExistsException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        ErrorDetails errorDetails = ErrorDetails.builder()
//                .message(ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";")))
//                .details(request.getDescription(false))
//                .timeStamp(LocalDateTime.now())
//                .build();
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage("Validation error")
                .build();

        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errorResponse.setValidationErrors(validationErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
