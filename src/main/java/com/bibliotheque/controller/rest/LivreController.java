package com.bibliotheque.controller.rest;

import com.bibliotheque.model.Livre;
import com.bibliotheque.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        return ResponseEntity.ok(livreService.getAllLivres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        return ResponseEntity.ok(livreService.saveLivre(livre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        if (!livreService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livre.setId(id);
        return ResponseEntity.ok(livreService.saveLivre(livre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        if (!livreService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Livre>> getLivresDisponibles() {
        return ResponseEntity.ok(livreService.getLivresDisponibles());
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Livre>> searchLivres(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String auteur) {
        return ResponseEntity.ok(livreService.searchLivres(titre, auteur));
    }
}