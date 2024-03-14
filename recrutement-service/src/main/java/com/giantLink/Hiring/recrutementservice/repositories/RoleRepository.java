package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(String roleName);
    // Role Search
    @Query("SELECT r FROM Role r WHERE LOWER(r.roleName) LIKE %:roleName%")
    List<Role> findByRoleContaining(@Param("roleName") String roleName);
}
