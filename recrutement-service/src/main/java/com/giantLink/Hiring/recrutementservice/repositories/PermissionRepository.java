package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByLabel(String label);

    // search
    @Query("SELECT p FROM Permission p WHERE LOWER(p.label) LIKE %:label%")
    List<Permission> findByPermissionContaining(@Param("label") String label);
}
