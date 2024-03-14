package com.giantLink.Hiring.recrutementservice.repositories;


import com.giantLink.Hiring.recrutementservice.entities.Cv; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv, Long> {

}
