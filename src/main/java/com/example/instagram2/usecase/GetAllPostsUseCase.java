package com.example.instagram2.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.instagram2.domain.model.Post;
import com.example.instagram2.domain.repository.PostRepository;

@Service
public class GetAllPostsUseCase {
    private final PostRepository postRepository;

    public GetAllPostsUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public List<Post> execute() {
        return this.postRepository.findAll();
    }
}
