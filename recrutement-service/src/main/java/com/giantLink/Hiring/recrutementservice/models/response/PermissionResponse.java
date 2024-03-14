package com.giantLink.Hiring.recrutementservice.models.response;

import lombok.Builder; 
import lombok.Getter;
import lombok.Setter;
import com.giantLink.Hiring.recrutementservice.enums.PermissionEnum;

@Getter
@Setter
@Builder
public class PermissionResponse {
    private Long id;

    private PermissionEnum label;
}
