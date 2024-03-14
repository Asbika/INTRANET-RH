package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContractResponse {
    private Long id;
    private String name;
    private String description;
    private Campaign campaign;
    private String message;
}
