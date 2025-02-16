package com.example.RacheleBurgio.Repository;

import com.example.RacheleBurgio.Entity.Postazione;
import com.example.RacheleBurgio.Entity.Prenotazione;
import com.example.RacheleBurgio.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByPostazioneAndData(Postazione postazione, Date data);
    List<Prenotazione> findByUtenteAndData(Utente utente, Date data);

}
