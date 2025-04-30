package com.bibliotheque.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "utilisateur")
    private List<Pret> prets;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<Pret> getPrets() {
        return prets;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPrets(List<Pret> prets) {
        this.prets = prets;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    // Méthode utilitaire
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}