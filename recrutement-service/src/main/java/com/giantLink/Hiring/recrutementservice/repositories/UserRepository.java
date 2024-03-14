package com.giantLink.Hiring.recrutementservice.repositories;


import com.giantLink.Hiring.recrutementservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String cin);
}
