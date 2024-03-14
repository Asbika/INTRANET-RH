package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank; 
import lombok.Builder;
import lombok.Data;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import java.util.List;

@Data
@Builder
public class QualificationRequest {


    @NotBlank(message = "qualification name  required")
    private String qualificationName;
    @NotBlank(message = "wording qualification  required")
    private String wording;
    private List<Candidacy> candidacies;

}
