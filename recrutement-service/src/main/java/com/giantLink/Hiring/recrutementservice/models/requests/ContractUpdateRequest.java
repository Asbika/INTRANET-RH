package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContractUpdateRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @Size(max = 500)
    private String description;
}
