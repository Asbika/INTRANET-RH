package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Candidate; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
