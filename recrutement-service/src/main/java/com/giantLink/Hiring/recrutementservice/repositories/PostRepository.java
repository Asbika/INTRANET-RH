package com.giantLink.Hiring.recrutementservice.repositories;

import com.giantLink.Hiring.recrutementservice.entities.Post; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository  extends JpaRepository<Post, Long> {
    Optional<Post> findByName(String name);
    Optional<List<Post>> findByTelecommuting(Enum telecommuting);
}
