package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import com.giantLink.Hiring.recrutementservice.entities.GlobalExperience;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CvUpdateRequest
{
        @NotNull(message = "Skills IDs are required")
        private List<@Positive Long> skillsId;

        @NotNull(message = "Education ID is required")
        @Positive(message = "Education ID must be positive")
        private Long educationId;

        @NotNull(message = "Domain ID is required")
        @Positive(message = "Domain ID must be positive")
        private Long domainId;

        @NotNull(message = "Languages IDs are required")
        private List<@Positive Long> languagesId;

        private List<GlobalExperience> globalExperiences;
}
