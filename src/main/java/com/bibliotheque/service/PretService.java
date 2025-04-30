package com.bibliotheque.service;

import com.bibliotheque.model.Livre;
import com.bibliotheque.model.Pret;
import com.bibliotheque.model.Utilisateur;
import com.bibliotheque.repository.LivreRepository;
import com.bibliotheque.repository.PretRepository;
import com.bibliotheque.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    private final PretRepository pretRepository;
    private final LivreRepository livreRepository;
    private final UtilisateurRepository utilisateurRepository;

    public PretService(PretRepository pretRepository,
                      LivreRepository livreRepository,
                      UtilisateurRepository utilisateurRepository) {
        this.pretRepository = pretRepository;
        this.livreRepository = livreRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public Pret createPret(Long livreId, Long utilisateurId, int dureeJours) {
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!livre.isDisponible()) {
            throw new RuntimeException("Le livre n'est pas disponible pour prêt");
        }

        Pret pret = new Pret();
        pret.setLivre(livre);
        pret.setUtilisateur(utilisateur);
        pret.setDatePret(LocalDateTime.now());
        pret.setDateRetourPrevue(LocalDateTime.now().plusDays(dureeJours));
        pret.setVersion(0);

        livre.setDisponible(false);
        livreRepository.save(livre);

        return pretRepository.save(pret);
    }

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Optional<Pret> getPretById(Long id) {
        return pretRepository.findById(id);
    }

    public List<Pret> getPretsByUtilisateur(Long utilisateurId) {
        return pretRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Pret> getCurrentPretsByUtilisateur(Long utilisateurId) {
        return pretRepository.findCurrentPretsByUtilisateur(utilisateurId);
    }

    public List<Pret> getOverduePrets() {
        return pretRepository.findOverduePrets(LocalDateTime.now());
    }

    @Transactional
    public Optional<Pret> retournerPret(Long pretId) {
        return pretRepository.findById(pretId).map(pret -> {
            pret.setDateRetourEffectif(LocalDateTime.now());
            
            Livre livre = pret.getLivre();
            livre.setDisponible(true);
            livreRepository.save(livre);
            
            return pretRepository.save(pret);
        });
    }

    public int countCurrentPretsByUtilisateur(Long utilisateurId) {
        return pretRepository.countCurrentPretsByUtilisateur(utilisateurId);
    }
}