package com.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prets")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime datePret;

    private LocalDateTime dateRetourPrevue;
    private LocalDateTime dateRetourEffectif;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getDatePret() {
        return datePret;
    }

    public LocalDateTime getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDateTime getDateRetourEffectif() {
        return dateRetourEffectif;
    }

    public int getVersion() {
        return version;
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

    public void setDatePret(LocalDateTime datePret) {
        this.datePret = datePret;
    }

    public void setDateRetourPrevue(LocalDateTime dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public void setDateRetourEffectif(LocalDateTime dateRetourEffectif) {
        this.dateRetourEffectif = dateRetourEffectif;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Méthode utilitaire
    public boolean estEnRetard() {
        return dateRetourEffectif == null && 
               LocalDateTime.now().isAfter(dateRetourPrevue);
    }

    @Override
    public String toString() {
        return "Pret{" +
               "id=" + id +
               ", datePret=" + datePret +
               ", dateRetourPrevue=" + dateRetourPrevue +
               ", dateRetourEffectif=" + dateRetourEffectif +
               ", livre=" + (livre != null ? livre.getTitre() : "null") +
               '}';
    }
}