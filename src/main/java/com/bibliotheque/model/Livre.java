package com.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "livres")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private boolean disponible = true;

    private LocalDate datePublication;

    @Version
    private int version;

    @OneToOne(mappedBy = "livre")
    private Pret pret;

    @OneToOne(mappedBy = "livre")
    private Reservation reservation;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public int getVersion() {
        return version;
    }

    public Pret getPret() {
        return pret;
    }

    public Reservation getReservation() {
        return reservation;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    // Méthode utilitaire pour la disponibilité
    public boolean estDisponible() {
        return this.disponible && this.pret == null && this.reservation == null;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", isbn='" + isbn + '\'' +
                ", disponible=" + disponible +
                ", datePublication=" + datePublication +
                '}';
    }
}