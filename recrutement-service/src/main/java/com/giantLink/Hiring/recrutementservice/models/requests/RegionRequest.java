package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegionRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    String name;
}
