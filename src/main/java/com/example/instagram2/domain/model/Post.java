package com.example.instagram2.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um post")
@Entity
@Table(name = "posts")
@Data
public class Post {
    @Schema(description = "ID único do post", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Schema(description = "Título do post", example = "Meu primeiro post")
    private String title;
    
    @Schema(description = "Conteúdo do post", example = "Este é o conteúdo do meu post...")
    private String content;

    public void validate() {
        if (this.title == null || this.title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (this.content == null || this.content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content is required");
        }
    }
}
