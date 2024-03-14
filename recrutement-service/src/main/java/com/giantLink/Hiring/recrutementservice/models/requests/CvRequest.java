package com.giantLink.Hiring.recrutementservice.models.requests;

import java.util.List; 
import com.giantLink.Hiring.recrutementservice.entities.Candidate;
import com.giantLink.Hiring.recrutementservice.entities.GlobalExperience;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CvRequest {
	@Valid
	private Candidate candidate;

	@NotEmpty(message = "Skills IDs are required")
	private List<@Positive Long> skillsId;

	@NotNull(message = "Education ID is required")
	@Positive(message = "Education ID must be positive")
	private Long educationId;

	@NotNull(message = "Domain ID is required")
	@Positive(message = "Domain ID must be positive")
	private Long domainId;

	@NotEmpty(message = "Languages IDs are required")
	private List<@Positive Long> languagesId;

	private List<@Valid GlobalExperience> globalExperiences;

}