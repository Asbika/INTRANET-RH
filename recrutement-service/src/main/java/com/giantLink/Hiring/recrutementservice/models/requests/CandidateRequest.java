package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
@Data
public class CandidateRequest {
    @NotBlank(message="Candidate first name required")
    private String firstName;
    @NotBlank(message="Candidate last name required")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "^(\\+?1\\s?)?\\(?(\\d{3})\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$", message = "Invalid phone number")
    private String phone;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Country is required")
    private String country;
    @NotNull(message = "Birthday date cannot be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthdayDate;
    @NotBlank(message = "Message is required")
    private String message;
    @NotBlank(message = "Availability is required")
    private String availability;
}
