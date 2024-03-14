package com.giantLink.Hiring.recrutementservice.models.response;


import lombok.AllArgsConstructor; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import com.giantLink.Hiring.recrutementservice.entities.Evaluation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResponse {

    private Long id;
    private String title;
    private Date interviewDate;
    private String location;
    private String interviewer;
    private String status;
    private Evaluation evaluation;
    private CandidateResponse candidate;
    private String message;

}
