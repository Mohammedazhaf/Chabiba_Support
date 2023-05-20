package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/commentaires"})
public class CommentaireController {
    @Autowired
    private CommentaireService commentaireService;

    @GetMapping({"/all"})
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/afficher/{id}")
    public Commentaire getCommentaireById(@PathVariable Long id) {
        return commentaireService.getCommentaireById(id);
    }

    @PostMapping({"/add"})
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireService.createCommentaire(commentaire);
    }

    @PutMapping("/update/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaireDetails) {
        return commentaireService.updateCommentaire(id, commentaireDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.ok().build();
    }
}

