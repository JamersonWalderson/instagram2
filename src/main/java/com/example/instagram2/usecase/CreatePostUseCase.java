package com.example.instagram2.usecase;

import org.springframework.stereotype.Service;

import com.example.instagram2.domain.model.Post;
import com.example.instagram2.domain.repository.PostRepository;

@Service
public class CreatePostUseCase {
    private final PostRepository postRepository;

    public CreatePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public Post execute(Post post) {
        post.validate();
        return this.postRepository.save(post);
    }
}
