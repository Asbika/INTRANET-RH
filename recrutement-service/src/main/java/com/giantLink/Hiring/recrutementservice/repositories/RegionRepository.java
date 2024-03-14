package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Region; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
 
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
}
