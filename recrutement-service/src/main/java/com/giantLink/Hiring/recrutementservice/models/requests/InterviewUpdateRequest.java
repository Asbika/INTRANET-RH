package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewUpdateRequest {

    @Size(max = 30)
    private String title;
    private Date interviewDate;
    @Size(max = 30)
    private String location;
    @Size(max = 50)
    private String interviewer;
    @Pattern(regexp = "^(SCHEDULED|POSTPONED|COMPLETED|CANCELLED)", message = "Please enter a valid value (SCHEDULED|POSTPONED|COMPLETED|CANCELLED)")
    private String status;
    @Size(max = 80)
    private String conclusion;
    @Size(max = 200)
    private String comments;

}
