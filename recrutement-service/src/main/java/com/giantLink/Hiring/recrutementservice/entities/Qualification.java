package com.giantLink.Hiring.recrutementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Qualifications")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qualificationName;
    private String wording;

    @OneToMany(mappedBy = "qualification")
    private List<Candidacy> candidacy;
}
