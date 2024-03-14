package com.giantLink.Hiring.recrutementservice.models.response;

import com.fasterxml.jackson.annotation.JsonFormat; 
import com.giantLink.Hiring.recrutementservice.entities.Contract;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.entities.Region;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CampaignResponse {
    private Long id;
    private String name;
    private String description;
    private int numberOfPosts;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date closingDate;
    private List<Region> regions;
    private List<Post> posts;
    private List<Contract> contracts;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private String message;

    @PrePersist
    void setCreatedAtField(){
        createdAt = new Date();
    }
    @PreUpdate
    void setUpdatedAtField(){
        updatedAt = new Date();
    }


}
