package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
	private Long id;
	private String name;
	private String description;
	private String language;
	private String keyWords;
	private Telecommuting telecommuting;
	private Campaign campaign;
	private Date updatedAt;
	private Date createdAt;
    private List<Candidacy> candidacies = new ArrayList<>();
    private String message;


}
