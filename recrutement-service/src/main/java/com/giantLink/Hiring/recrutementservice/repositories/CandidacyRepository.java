package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Candidacy; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidacyRepository extends JpaRepository<Candidacy,Long> {

    Optional<Candidacy> findByApplicationName(String aplication);
    Optional<List<Candidacy>> findByPostId(Long id);

}
