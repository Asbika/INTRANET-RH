package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Qualification; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<Qualification,Long> {

    Optional<Qualification> findByQualificationName(String qualificationName);
}
