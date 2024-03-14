package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @ManyToMany(mappedBy = "regions", cascade = CascadeType.DETACH)
    @JsonBackReference
    private List<Campaign> campaigns;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    void setCreatedAtField(){
        createdAt = new Date();
    }
    @PreUpdate
    void setUpdatedAtField(){
        updatedAt = new Date();
    }

}
