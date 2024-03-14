package com.giantLink.Hiring.recrutementservice.models.requests;


import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewAddRequest {

    @NotBlank(message="Interview title required")
    private String title;
    @NotNull(message="Please enter the date")
    private Date interviewDate;
    @NotBlank(message="Interview location required")
    private String location;
    @NotBlank(message="Please enter the interviewer name")
    private String interviewer;
    @NotNull(message="Candidate Id cannot be null")
    private Long candidateId;

    // When creating a new Interview, the default status is 'SCHEDULED,'
    // and the evaluation is initialized with 'no values yet'. You can later update
    // the evaluation with the necessary information and change the status as needed.

}
