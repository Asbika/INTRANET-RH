package com.giantLink.Hiring.recrutementservice.models.response;

import lombok.Builder; 
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class CandidateResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Date birthdayDate;
    private String message;
    private String availability;
}
