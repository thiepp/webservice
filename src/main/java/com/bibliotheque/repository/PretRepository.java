package com.bibliotheque.repository;

import com.bibliotheque.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PretRepository extends JpaRepository<Pret, Long> {

    List<Pret> findByUtilisateurId(Long utilisateurId);
    
    List<Pret> findByLivreId(Long livreId);
    
    @Query("SELECT p FROM Pret p WHERE p.dateRetourEffectif IS NULL AND p.dateRetourPrevue < :now")
    List<Pret> findOverduePrets(@Param("now") LocalDateTime now);
    
    Optional<Pret> findByLivreIdAndDateRetourEffectifIsNull(Long livreId);
    
    @Query("SELECT p FROM Pret p WHERE p.utilisateur.id = :utilisateurId AND p.dateRetourEffectif IS NULL")
    List<Pret> findCurrentPretsByUtilisateur(@Param("utilisateurId") Long utilisateurId);
    
    @Query("SELECT COUNT(p) FROM Pret p WHERE p.utilisateur.id = :utilisateurId AND p.dateRetourEffectif IS NULL")
    int countCurrentPretsByUtilisateur(@Param("utilisateurId") Long utilisateurId);
}