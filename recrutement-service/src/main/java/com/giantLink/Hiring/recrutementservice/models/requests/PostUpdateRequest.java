package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size;
import lombok.*;
import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostUpdateRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    String name;
    @Size(max = 500)
    String description;
    @Size(max = 20)
    String language;
    @Size(max = 300)
    String keyWords;
    Telecommuting telecommuting;
    Long campaignId;
    private List<Long> candidatures_Id = new ArrayList<>();

}
