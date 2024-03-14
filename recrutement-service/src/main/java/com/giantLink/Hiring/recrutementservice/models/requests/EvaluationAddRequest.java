package com.giantLink.Hiring.recrutementservice.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationAddRequest {
    private String conclusion;
    private String comments;
}
