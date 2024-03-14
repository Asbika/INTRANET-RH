package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.giantLink.Hiring.recrutementservice.enums.CandidateType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;
    private String address;
    private String city;
    private String country;
    private Date birthdayDate;
    private String message;
    private String availability;


    @OneToOne(
    		cascade = CascadeType.REMOVE)
    @JoinColumn(name= "cv_id")
    @JsonBackReference
    private Cv cv;
}
