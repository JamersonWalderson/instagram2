package com.example.instagram2.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.instagram2.domain.model.Post;


public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
}
