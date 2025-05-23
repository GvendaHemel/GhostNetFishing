package com.ghostnet;

import com.ghostnet.dao.GeisternetzRepository;
import com.ghostnet.dao.PersonRepository;
import com.ghostnet.model.Geisternetz;
import com.ghostnet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Component("geisternetzBean")  // Spring-Bean für JSF
@SessionScope  // Spring Session-Scoped Alternative
public class GeisternetzBean implements Serializable {

    private static final long serialVersionUID = 1L;  // Wichtig für Serializable

    static {
        System.out.println("🚀 GeisternetzBean wird geladen...");
    }

    private Geisternetz geisternetz;
    private List<Geisternetz> geisternetze;
    private List<Person> personen;  // Liste der Personen für die Auswahl

    @Autowired
    private GeisternetzRepository geisternetzRepository;

    @Autowired
    private PersonRepository personRepository;  // Repository für Personen

    @PostConstruct
    public void init() {
        try {
            System.out.println("🔍 INIT: Starte Initialisierung von GeisternetzBean...");
            geisternetz = new Geisternetz();
            geisternetze = geisternetzRepository.findAll();
            personen = personRepository.findAll();  // Lade alle Personen

            System.out.println("✅ Geisternetze erfolgreich geladen. Anzahl: " + geisternetze.size());
            System.out.println("✅ Personen erfolgreich geladen. Anzahl: " + personen.size());
        } catch (Exception e) {
            System.err.println("❌ Fehler beim Laden der Daten: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getter und Setter
    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }

    public List<Geisternetz> getGeisternetze() {
        return geisternetze;
    }

    public List<Person> getPersonen() {
        return personen;
    }

    @Transactional
    public void speichern() {
        try {
            System.out.println("📌 SPEICHERN: Versuche, Geisternetz mit GPS-Koordinaten zu speichern: " + geisternetz.getGpsKoordinaten());
            geisternetzRepository.save(geisternetz);
            geisternetze = geisternetzRepository.findAll();  // Aktualisiere die Liste

            System.out.println("✅ Geisternetz erfolgreich gespeichert! Neue Anzahl: " + geisternetze.size());
            geisternetz = new Geisternetz();  // Zurücksetzen des Formulars
        } catch (Exception e) {
            System.err.println("❌ Fehler beim Speichern des Geisternetzes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // **Neue Methode: Löschen eines Geisternetzes**
    @Transactional
    public void loeschen(Long id) {
        try {
            System.out.println("🗑 LÖSCHEN: Versuche, Geisternetz mit ID " + id + " zu löschen...");
            Optional<Geisternetz> netz = geisternetzRepository.findById(id);

            if (netz.isPresent()) {
                geisternetzRepository.delete(netz.get());
                geisternetze = geisternetzRepository.findAll(); // Aktualisiere die Liste
                System.out.println("✅ Geisternetz mit ID " + id + " wurde gelöscht.");
            } else {
                System.err.println("❌ Kein Geisternetz mit ID " + id + " gefunden.");
            }
        } catch (Exception e) {
            System.err.println("❌ Fehler beim Löschen des Geisternetzes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

