package com.example.RacheleBurgio.Repository;

import com.example.RacheleBurgio.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByEmail(String email);
    List<Utente> findByNomeContaining(String nome);
}