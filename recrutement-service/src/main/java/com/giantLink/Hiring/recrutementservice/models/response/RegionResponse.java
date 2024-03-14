package com.giantLink.Hiring.recrutementservice.models.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RegionResponse {
    int id;
    String name;
    Date updatedAt;
    Date createdAt;
    private String message;
}
