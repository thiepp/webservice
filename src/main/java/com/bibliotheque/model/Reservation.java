package com.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateReservation;

    private LocalDateTime dateExpiration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    public enum StatutReservation {
        EN_ATTENTE,
        CONFIRMEE,
        ANNULEE
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public Livre getLivre() {
        return livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Méthodes utilitaires
    public boolean estExpiree() {
        return statut == StatutReservation.CONFIRMEE && 
               dateExpiration != null && 
               LocalDateTime.now().isAfter(dateExpiration);
    }

    @Override
    public String toString() {
        return "Reservation{" +
               "id=" + id +
               ", statut=" + statut +
               ", dateReservation=" + dateReservation +
               ", dateExpiration=" + dateExpiration +
               ", livre=" + (livre != null ? livre.getTitre() : "null") +
               '}';
    }
}