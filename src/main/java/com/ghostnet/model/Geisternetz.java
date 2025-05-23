package com.ghostnet.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "GPS-Koordinaten dürfen nicht leer sein")
    private String gpsKoordinaten;

    @NotBlank(message = "Größe darf nicht leer sein")
    private String groesse;

    @NotNull(message = "Status muss ausgewählt werden")
    @Enumerated(EnumType.STRING)
    private NetzStatus status;

    @ManyToOne
    @JoinColumn(name = "meldende_person_id")
    private Person meldendePerson;

    @ManyToOne
    @JoinColumn(name = "bergende_person_id")
    private Person bergendePerson;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGpsKoordinaten() { return gpsKoordinaten; }
    public void setGpsKoordinaten(String gpsKoordinaten) { this.gpsKoordinaten = gpsKoordinaten; }

    public String getGroesse() { return groesse; }
    public void setGroesse(String groesse) { this.groesse = groesse; }

    public NetzStatus getStatus() { return status; }
    public void setStatus(NetzStatus status) { this.status = status; }

    public Person getMeldendePerson() { return meldendePerson; }
    public void setMeldendePerson(Person meldendePerson) { this.meldendePerson = meldendePerson; }

    public Person getBergendePerson() { return bergendePerson; }
    public void setBergendePerson(Person bergendePerson) { this.bergendePerson = bergendePerson; }
}

