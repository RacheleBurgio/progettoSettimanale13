package com.example.RacheleBurgio.Repository;

import com.example.RacheleBurgio.Entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    List<Edificio> findByNome(String nome);
    List<Edificio> findByCitta(String citta);
    List<Edificio> findByNomeAndCitta(String nome, String citta);

}
