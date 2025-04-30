package com.bibliotheque.service;

import com.bibliotheque.model.Livre;
import com.bibliotheque.model.Reservation;
import com.bibliotheque.model.Utilisateur;
import com.bibliotheque.repository.LivreRepository;
import com.bibliotheque.repository.ReservationRepository;
import com.bibliotheque.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LivreRepository livreRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ReservationService(ReservationRepository reservationRepository,
                            LivreRepository livreRepository,
                            UtilisateurRepository utilisateurRepository) {
        this.reservationRepository = reservationRepository;
        this.livreRepository = livreRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Livre livre = livreRepository.findById(reservation.getLivre().getId())
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
        Utilisateur utilisateur = utilisateurRepository.findById(reservation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!livre.isDisponible()) {
            throw new RuntimeException("Le livre n'est pas disponible pour réservation");
        }

        reservation.setDateReservation(LocalDateTime.now());
        reservation.setDateExpiration(LocalDateTime.now().plusDays(7));
        reservation.setStatut(Reservation.StatutReservation.CONFIRMEE);

        livre.setDisponible(false);
        livreRepository.save(livre);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getReservationsByUtilisateur(Long utilisateurId) {
        return reservationRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Reservation> getReservationsByLivre(Long livreId) {
        return reservationRepository.findByLivreId(livreId);
    }

    public List<Reservation> getReservationsByStatut(Reservation.StatutReservation statut) {
        return reservationRepository.findByStatut(statut);
    }

    @Transactional
    public Optional<Reservation> annulerReservation(Long id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStatut(Reservation.StatutReservation.ANNULEE);
            
            Livre livre = reservation.getLivre();
            livre.setDisponible(true);
            livreRepository.save(livre);
            
            return reservationRepository.save(reservation);
        });
    }

    public List<Reservation> getExpiredReservations() {
        return reservationRepository.findExpiredReservations(LocalDateTime.now());
    }
}