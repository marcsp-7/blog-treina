package com.blog.treina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.treina.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
