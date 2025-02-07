package com.example.RacheleBurgio.Repository;

import com.example.RacheleBurgio.Entity.Postazione;
import com.example.RacheleBurgio.Entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificio_Citta(String tipo, String citta);
    List<Postazione> findByCodice(String codice);}

