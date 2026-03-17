package com.example.instagram2.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.instagram2.domain.model.Post;

public interface SpringDataPostRepository extends JpaRepository<Post, Long> {
}
