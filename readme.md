# Ghost Net Fishing

Ghost Net Fishing ist eine Webanwendung, die im Rahmen einer Fallstudie im Studiengang Informatik an der IU Internationale Hochschule entwickelt wurde. Ziel der Anwendung ist es, die Meldung und Bergung sogenannter Geisternetze in den Weltmeeren digital zu unterstützen. Geisternetze sind herrenlose Fischernetze, die im Meer treiben und eine ernste Bedrohung für Meereslebewesen und die Umwelt darstellen.

Die Anwendung ermöglicht es meldenden Personen, Netze mit Standortdaten und geschätzter Größe zu erfassen. Bergende Personen können sich für die Bergung registrieren und den Status eines Netzes aktualisieren. Eine Anmeldung oder Registrierung ist nicht erforderlich – die Bedienung ist bewusst niedrigschwellig gehalten. Der Status eines Netzes lässt sich im Laufe des Prozesses verändern: von „GEMELDET“ über „IN BERGUNG“ bis hin zu „GEBORGEN“ oder „VERSCHOLLEN“. 

Die Anwendung wurde als funktionaler Prototyp konzipiert, mit dem Ziel, eine einfache, benutzerfreundliche Oberfläche bereitzustellen, die gleichzeitig eine verlässliche Datenspeicherung über eine relationale Datenbank sicherstellt. Die technische Umsetzung basiert auf modernen Java-Technologien im Backend und einem komponentenbasierten Frontend mit JSF.

---

## Technologien

- **Frontend:** HTML, JSF (JavaServer Faces), ggf. PrimeFaces  
- **Backend:** Java, Spring Boot, CDI, JPA (Hibernate)  
- **Datenbank:** MySQL  
- **Architektur:** MVC (Model-View-Controller)

---

## Datenmodell (Kurzbeschreibung)

- **Geisternetz:** ID, Koordinaten, Größe, Status, meldende/bergende Person  
- **Person:** ID, Name, Telefonnummer  
- **Bergung:** Zuordnung zwischen Geisternetz und bergender Person

---

## Voraussetzungen

- Java 17 oder neuer  
- Maven  
- Laufender MySQL-Server  
- Ein moderner Webbrowser (z. B. Chrome oder Firefox)

---

## Setup und Ausführung

1. Repository klonen:

   ```bash
   git clone https://github.com/GvendaHemel/GhostNetFishing.git
   cd GhostNetFishing
   ```

Projekt bauen:
Das Projekt wird mit dem folgenden Befehl gebaut. Dadurch wird der Quellcode kompiliert und alle notwendigen Abhängigkeiten automatisch heruntergeladen.
```bash 
mvn clean install
```


MySQL-Datenbank starten:
Danach muss die MySQL-Datenbank gestartet werden.
Falls notwendig, kann ein vorbereitetes SQL-Skript aus dem Verzeichnis /db ausgeführt werden, um Tabellen und Beispieldaten zu erstellen

Sobald die Datenbank bereitsteht, kann die Anwendung mit folgendem Befehl gestartet werden: 
```bash
mvn spring-boot:run
```

Nach dem erfolgreichen Start ist die Weboberfläche über folgenden Link erreichbar:
http://localhost:8080



Autorin
Dieses Projekt wurde im Rahmen einer Fallstudie an der IU Internationale Hochschule entwickelt von:

Gvantsa Gogrichiani
GitHub: @GvendaHemel


