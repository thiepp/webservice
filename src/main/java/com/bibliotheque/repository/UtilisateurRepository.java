package com.bibliotheque.repository;

import com.bibliotheque.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM Utilisateur u LEFT JOIN FETCH u.prets WHERE u.id = :id")
    Optional<Utilisateur> findByIdWithPrets(@Param("id") Long id);
    
    @Query("SELECT u FROM Utilisateur u LEFT JOIN FETCH u.reservations WHERE u.id = :id")
    Optional<Utilisateur> findByIdWithReservations(@Param("id") Long id);
    
    List<Utilisateur> findByRole(String role);
}