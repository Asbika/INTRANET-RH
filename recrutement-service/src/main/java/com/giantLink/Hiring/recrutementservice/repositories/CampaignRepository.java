package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByName(String name);
}
