package com.blog.treina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.treina.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
