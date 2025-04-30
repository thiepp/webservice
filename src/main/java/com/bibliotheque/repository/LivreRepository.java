package com.bibliotheque.repository;

import com.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    Optional<Livre> findByIsbn(String isbn);
    
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    
    List<Livre> findByDisponibleTrue();
    
    @Query("SELECT l FROM Livre l WHERE lower(l.titre) LIKE lower(concat('%', :keyword, '%')) OR lower(l.auteur) LIKE lower(concat('%', :keyword, '%'))")
    List<Livre> searchByKeyword(@Param("keyword") String keyword);
    
    boolean existsByIsbn(String isbn);
    
    @Query("SELECT l FROM Livre l LEFT JOIN FETCH l.pret WHERE l.id = :id")
    Optional<Livre> findByIdWithPret(@Param("id") Long id);

	List<Livre> findByTitreContainingIgnoreCaseAndAuteurContainingIgnoreCase(String titre, String auteur);
}