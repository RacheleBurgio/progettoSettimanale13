package com.example.RacheleBurgio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne
   @JoinColumn(name = "postazione_id")
   private Postazione postazione;

   @ManyToOne
   @JoinColumn(name = "utente_id")
   private Utente utente;

   private Date data;
}