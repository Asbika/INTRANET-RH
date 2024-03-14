package com.giantLink.Hiring.recrutementservice.models.requests;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
public class CandidacyRequest {

	@NotNull
    private String applicationName;
    @JsonIgnore
    private Long cvId;
    @NotBlank
    private Long qualificationId;
    @NotBlank
    private Long postId;
}
