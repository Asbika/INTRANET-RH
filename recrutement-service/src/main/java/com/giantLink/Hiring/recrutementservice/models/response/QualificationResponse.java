package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Candidacy; 
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class QualificationResponse {

    private Long id;
    private String qualificationName;
    private String wording;
    private List<Candidacy> candidacies;
    private String message;

}
