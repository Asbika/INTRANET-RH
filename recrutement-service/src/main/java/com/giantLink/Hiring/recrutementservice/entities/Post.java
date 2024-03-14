package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference; 
import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Posts")
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(length = 20)
    private String language;
    @Column(length = 300)
    private String keyWords;
    @Column
    private double salary;
    @Enumerated(EnumType.STRING)
    private Telecommuting telecommuting;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "campaign_id")
    @JsonBackReference
    private Campaign campaign;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<Candidacy> candidacies = new ArrayList<>();


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
