package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignUpdateRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @Size(max = 500)
    private String description;
    private int numberOfPosts;
    private Date closingDate;

}
