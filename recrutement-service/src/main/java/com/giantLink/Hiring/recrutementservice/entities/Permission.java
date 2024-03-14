package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import com.giantLink.Hiring.recrutementservice.enums.PermissionEnum;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionEnum label;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
    @JsonBackReference
    private List<Role> roles;

}
