package com.bibliotheque.repository;

import com.bibliotheque.model.Reservation;
import com.bibliotheque.model.Reservation.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByLivreId(Long livreId);
    
    List<Reservation> findByUtilisateurId(Long utilisateurId);
    
    List<Reservation> findByStatut(StatutReservation statut);
    
    @Query("SELECT r FROM Reservation r WHERE r.dateExpiration < :now AND r.statut = 'CONFIRMEE'")
    List<Reservation> findExpiredReservations(@Param("now") LocalDateTime now);
    
    @Query("SELECT r FROM Reservation r WHERE r.livre.id = :livreId AND r.statut = 'CONFIRMEE' ORDER BY r.dateReservation ASC")
    List<Reservation> findActiveReservationsForLivre(@Param("livreId") Long livreId);
    
    boolean existsByLivreIdAndStatut(Long livreId, StatutReservation statut);
}