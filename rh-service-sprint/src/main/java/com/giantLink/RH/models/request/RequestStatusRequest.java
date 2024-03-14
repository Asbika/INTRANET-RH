package com.giantLink.RH.models.request;

import com.giantLink.RH.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestStatusRequest {
    @NotBlank(message = "Type cannot be blank")
    private State type;
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String messageDetails;
}
