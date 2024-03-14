package com.giantLink.Hiring.recrutementservice.entities;

import com.giantLink.Hiring.recrutementservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conclusion;
    private String comments;


    @Column(name = "status", columnDefinition = "ENUM('hold', 'raedy','accepted','rejected')")
    @Enumerated(EnumType.STRING)
    private Status status;

}
