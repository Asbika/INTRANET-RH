package com.giantLink.Hiring.recrutementservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date interviewDate;
    private String location;
    private String interviewer;
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    private Evaluation evaluation;
    @OneToOne
    private Candidate candidate;




}
