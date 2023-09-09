package com.blog.treina.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.treina.model.Post;
import com.blog.treina.repository.PostRepository;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<Post> cadastrarPost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> listarPosts(Post post) {
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> listarPostPeloId(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable("id") Long id, @RequestBody Post post) {
        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isPresent()) {
            postExistente.get().setTitulo(post.getTitulo());
            postExistente.get().setConteudo(post.getConteudo());
            postExistente.get().setDataDeCriacao(post.getDataDeCriacao());
            postExistente.get().setAutor(post.getAutor());

            return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post deletado com sucesso!");
    }

    @Autowired
    private PostRepository postRepository;
}
