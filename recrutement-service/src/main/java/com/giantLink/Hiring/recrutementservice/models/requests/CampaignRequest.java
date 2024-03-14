package com.giantLink.Hiring.recrutementservice.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Builder
public class CampaignRequest {
    private Long id;
    private String name;
    private String description;
    private int numberOfPosts;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date closingDate;
}
