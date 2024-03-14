package com.giantLink.Hiring.recrutementservice.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContractRequest {
    private String name;
    private String description;
    private Long campaign_id;
}
