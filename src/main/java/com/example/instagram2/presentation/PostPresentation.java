package com.example.instagram2.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram2.usecase.CreatePostUseCase;
import com.example.instagram2.usecase.GetAllPostsUseCase;
import com.example.instagram2.domain.model.Post;
import com.example.instagram2.presentation.dto.CreatePostRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Posts", description = "Operações relacionadas a posts")
@RestController
@RequestMapping("/api/posts")
public class PostPresentation {
    private final CreatePostUseCase createPostUseCase;
    private final GetAllPostsUseCase getAllPostsUseCase;
    
    public PostPresentation(CreatePostUseCase createPostUseCase, GetAllPostsUseCase getAllPostsUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.getAllPostsUseCase = getAllPostsUseCase;
    }
    
    @Operation(summary = "Criar um novo post", description = "Cria um novo post com título e conteúdo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Post criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody CreatePostRequest request) {
        // Converte DTO para domain model
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        
        Post saved = this.createPostUseCase.execute(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Listar todos os posts", description = "Retorna uma lista com todos os posts cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posts listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        List<Post> posts = this.getAllPostsUseCase.execute();
        return ResponseEntity.ok(posts);
    }
}
