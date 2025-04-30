package com.bibliotheque.service;

import com.bibliotheque.model.Livre;
import com.bibliotheque.repository.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> getLivresDisponibles() {
        return livreRepository.findByDisponibleTrue();
    }

    public List<Livre> searchLivres(String titre, String auteur) {
        if (titre != null && auteur != null) {
            return livreRepository.findByTitreContainingIgnoreCaseAndAuteurContainingIgnoreCase(titre, auteur);
        } else if (titre != null) {
            return livreRepository.findByTitreContainingIgnoreCase(titre);
        } else if (auteur != null) {
            return livreRepository.findByAuteurContainingIgnoreCase(auteur);
        }
        return livreRepository.findAll();
    }

    public boolean existsById(Long id) {
        return livreRepository.existsById(id);
    }

    public Optional<Livre> findByIsbn(String isbn) {
        return livreRepository.findByIsbn(isbn);
    }

    public void updateDisponibilite(Long livreId, boolean disponible) {
        livreRepository.findById(livreId).ifPresent(livre -> {
            livre.setDisponible(disponible);
            livreRepository.save(livre);
        });
    }
}