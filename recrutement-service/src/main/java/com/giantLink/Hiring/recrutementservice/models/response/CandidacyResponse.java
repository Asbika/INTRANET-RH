package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Cv;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.entities.Qualification;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CandidacyResponse {
	private Long id;
    private String applicationName;
    private Date candidacyDate;
    private Cv cv;
    private Qualification qualification;
    private Post post;
    private String message;


}
