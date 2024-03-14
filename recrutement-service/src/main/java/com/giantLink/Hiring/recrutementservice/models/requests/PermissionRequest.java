package com.giantLink.Hiring.recrutementservice.models.requests;

import lombok.*;
import com.giantLink.Hiring.recrutementservice.enums.PermissionEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {

    private PermissionEnum label;
}
