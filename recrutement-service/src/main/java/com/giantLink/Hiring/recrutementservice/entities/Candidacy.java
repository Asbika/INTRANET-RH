package com.giantLink.Hiring.recrutementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="Candidacy")
public class Candidacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicationName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy" )
    private Date candidacyDate;

    @OneToOne
    @JoinColumn(name = "cv_id")
    @JsonBackReference
    @JsonIgnore
    private Cv cv;

    @OneToOne
    @JsonBackReference
    private Qualification qualification;


    @ManyToOne
    @JoinColumn(name = "poste_id")
    @JsonBackReference
    private Post post;
    
    @PrePersist
    private void onCreate() {
        candidacyDate = new Date();
        
    }


}
