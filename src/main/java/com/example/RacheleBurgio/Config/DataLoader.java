package com.example.RacheleBurgio.Config;

import com.example.RacheleBurgio.Entity.Edificio;
import com.example.RacheleBurgio.Entity.Postazione;
import com.example.RacheleBurgio.Entity.Prenotazione;
import com.example.RacheleBurgio.Entity.Utente;
import com.example.RacheleBurgio.Repository.EdificioRepository;
import com.example.RacheleBurgio.Repository.PostazioneRepository;
import com.example.RacheleBurgio.Repository.PrenotazioneRepository;
import com.example.RacheleBurgio.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EdificioRepository edificioRepository;
    @Autowired
    private PostazioneRepository postazioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto nel sistema di gestione prenotazioni!");


        System.out.println("Inserisci il nome dell'edificio:");
        String nomeEdificio = scanner.nextLine();

        System.out.println("Inserisci l'indirizzo dell'edificio:");
        String indirizzoEdificio = scanner.nextLine();

        System.out.println("Inserisci la città dell'edificio:");
        String cittaEdificio = scanner.nextLine();

        Edificio edificio = new Edificio();
        edificio.setNome(nomeEdificio);
        edificio.setIndirizzo(indirizzoEdificio);
        edificio.setCitta(cittaEdificio);
        edificioRepository.save(edificio);

        System.out.println("Edificio aggiunto con successo!");


        System.out.println("Inserisci il codice della postazione:");
        String codicePostazione = scanner.nextLine();

        System.out.println("Inserisci la descrizione della postazione:");
        String descrizionePostazione = scanner.nextLine();

        System.out.println("Inserisci il tipo di postazione (PRIVATO, OPENSPACE, SALA RIUNIONI):");
        String tipoPostazione = scanner.nextLine();

        System.out.println("Inserisci il numero massimo di occupanti:");
        int numeroMassimoOccupanti = Integer.parseInt(scanner.nextLine());

        Postazione postazione = new Postazione();
        postazione.setCodice(codicePostazione);
        postazione.setDescrizione(descrizionePostazione);
        postazione.setTipo(tipoPostazione);
        postazione.setNumeroMassimoOccupanti(numeroMassimoOccupanti);
        postazione.setEdificio(edificio);
        postazioneRepository.save(postazione);

        System.out.println("Postazione aggiunta con successo!");


        System.out.println("Inserisci lo username dell'utente:");
        String username = scanner.nextLine();

        System.out.println("Inserisci il nome completo dell'utente:");
        String nomeUtente = scanner.nextLine();

        System.out.println("Inserisci l'email dell'utente:");
        String emailUtente = scanner.nextLine();

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setNome(nomeUtente);
        utente.setEmail(emailUtente);
        utenteRepository.save(utente);

        System.out.println("Utente aggiunto con successo!");


        System.out.println("Vuoi fare una prenotazione? (Sì/No)");
        String risposta = scanner.nextLine();

        if (risposta.equalsIgnoreCase("si")) {
            System.out.println("Inserisci lo username dell'utente che prenota:");
            String usernameUtente = scanner.nextLine();
            Utente utentePrenotante = utenteRepository.findByUsername(usernameUtente)
                    .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

            System.out.println("Elenco delle postazioni disponibili:");
            List<Postazione> postazioni = postazioneRepository.findAll();
            for (Postazione postazioneItem : postazioni) {
                System.out.println(postazioneItem.getCodice() + ": " + postazioneItem.getDescrizione() + " - " + postazioneItem.getTipo());
            }

            System.out.println("Inserisci il codice della postazione che desideri prenotare:");
            String codicePostazionePrenotata = scanner.nextLine();
            Postazione postazioneSelezionata = postazioneRepository.findByCodice(codicePostazionePrenotata)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata"));

            System.out.println("Inserisci la data per la prenotazione (yyyy-MM-dd):");
            String dataString = scanner.nextLine();
            Date dataPrenotazione = java.sql.Date.valueOf(dataString);

            List<Prenotazione> prenotazioniEsistenti = prenotazioneRepository.findByPostazioneAndData(postazioneSelezionata, dataPrenotazione);
            if (!prenotazioniEsistenti.isEmpty()) {
                System.out.println("La postazione è già prenotata per questa data.");
            } else {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setUtente(utentePrenotante);
                prenotazione.setPostazione(postazioneSelezionata);
                prenotazione.setData(dataPrenotazione);
                prenotazioneRepository.save(prenotazione);

                System.out.println("Prenotazione effettuata con successo!");
            }
        } else if (risposta.equalsIgnoreCase("no")) {
            System.out.println("Hai scelto di non fare una prenotazione. Arrivederci!");
            System.exit(0);
        }
        }
    }

