package com.example.instagram2.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.instagram2.domain.model.Post;
import com.example.instagram2.domain.repository.PostRepository;

@Repository
public class JPAPostRepository implements PostRepository {
    
    private final SpringDataPostRepository springDataRepository;
    
    public JPAPostRepository(SpringDataPostRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }
    
    @Override
    public Post save(Post post) {
        return springDataRepository.save(post);
    }
    
    @Override
    public Optional<Post> findById(Long id) {
        return springDataRepository.findById(id);
    }
    
    @Override
    public List<Post> findAll() {
        return springDataRepository.findAll();
    }
}