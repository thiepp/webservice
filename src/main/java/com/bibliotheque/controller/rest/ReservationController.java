package com.bibliotheque.controller.rest;

import com.bibliotheque.model.Reservation;
import com.bibliotheque.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Reservation> annulerReservation(@PathVariable Long id) {
        return reservationService.annulerReservation(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Reservation>> getReservationsByUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(reservationService.getReservationsByUtilisateur(utilisateurId));
    }

    @GetMapping("/livre/{livreId}")
    public ResponseEntity<List<Reservation>> getReservationsByLivre(@PathVariable Long livreId) {
        return ResponseEntity.ok(reservationService.getReservationsByLivre(livreId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Reservation>> getReservationsByStatut(
            @PathVariable Reservation.StatutReservation statut) {
        return ResponseEntity.ok(reservationService.getReservationsByStatut(statut));
    }
}