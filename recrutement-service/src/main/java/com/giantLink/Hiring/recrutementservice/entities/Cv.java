package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cv {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY,
    		cascade = CascadeType.REMOVE)
	@JoinTable(name = "cv_skills")
    private List<Skill> skills ;

    @OneToMany(mappedBy = "cv",
    		cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<GlobalExperience> globalExperiences;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education ;

    @ManyToMany(fetch = FetchType.LAZY,
    		cascade = CascadeType.REMOVE)
	@JoinTable(name = "cv_languages")
    private List<Language> languages;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cv")

    @JsonManagedReference
    @JsonIgnore
    private Candidacy candidacy;
    
    @OneToOne(fetch = FetchType.EAGER,
    		cascade = CascadeType.REMOVE, mappedBy = "cv", orphanRemoval = true)
    private Candidate candidate;

}
