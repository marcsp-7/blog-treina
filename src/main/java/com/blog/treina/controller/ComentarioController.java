package com.blog.treina.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.treina.model.Comentario;
import com.blog.treina.repository.ComentarioRepository;

@RestController
@RequestMapping(value = "tb_comentarios")
public class ComentarioController {

    @PostMapping
    public ResponseEntity<Comentario> cadastrarComentario(@RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarTextoDoComentario(@PathVariable("id") Long id,
            @RequestBody Comentario comentario) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);

        if (comentarioExistente.isPresent()) {
            comentarioExistente.get().setTexto(comentario.getTexto());

            return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.save(comentarioExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarComentario(@PathVariable("id") Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if (comentario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        comentarioRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comentário deletado com sucesso!");
    }

    @Autowired
    private ComentarioRepository comentarioRepository;

}
