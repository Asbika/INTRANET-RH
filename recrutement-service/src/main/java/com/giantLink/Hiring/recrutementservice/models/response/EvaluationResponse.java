package com.giantLink.Hiring.recrutementservice.models.response;

import lombok.AllArgsConstructor; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResponse {
    private String conclusion;
    private String comments;
}
