package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 500)
    private String description;
    private int numberOfPosts;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date closingDate;

    @OneToMany(mappedBy = "campaign",cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Post> posts;


    @OneToMany(mappedBy = "campaign")
    @JsonBackReference
    private List<Contract> contracts;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "campaigns_regions",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    @JsonBackReference
    private List<Region> regions;

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
