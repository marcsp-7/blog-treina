package com.blog.treina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.treina.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
