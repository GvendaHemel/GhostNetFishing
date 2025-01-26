Ghost Net Fishing

Projektbeschreibung

Ghost Net Fishing ist eine Webanwendung, die darauf abzielt, die Meldung und Bergung sogenannter Geisternetze in den Weltmeeren zu erleichtern. Geisternetze sind herrenlose Fischernetze, die im Meer treiben und eine erhebliche Gefahr für Meereslebewesen und die Umwelt darstellen.

Die Anwendung ermöglicht es:

Meldenden Personen, Geisternetze mit Standortdaten und geschätzter Größe zu erfassen.

Bergenden Personen, sich für die Bergung von Geisternetzen zu registrieren und deren Status zu aktualisieren.

Den aktuellen Status der gemeldeten Netze einzusehen.

Projektziele

Entwicklung eines funktionalen Prototyps zur Erfassung und Verwaltung von Geisternetzen.

Bereitstellung einer benutzerfreundlichen Weboberfläche für meldende und bergende Personen.

Gewährleistung einer sicheren und effizienten Datenverwaltung durch persistente Speicherung in einer relationalen Datenbank.

Anforderungen

Die Webanwendung soll folgende Kernfunktionen umfassen:

MUST: Meldende Personen können Geisternetze (anonym) erfassen.

MUST: Bergende Personen können sich zur Bergung registrieren.

MUST: Bergende Personen können den Status von Geisternetzen aktualisieren.

COULD: Kartenbasierte Anzeige der noch nicht geborgenen Netze.

COULD: Benutzerfreundliche Filter- und Suchfunktionen.

Installation und Nutzung

Voraussetzungen

Java 11+

Maven

MySQL-Datenbank

Ein Webbrowser zur Nutzung der Anwendung

Schritte zur Installation

Repository klonen:

git clone https://github.com/yourusername/ghost-net-fishing.git

In das Projektverzeichnis wechseln:

cd ghost-net-fishing

Abhängigkeiten installieren und Projekt kompilieren:

mvn clean install

Datenbank einrichten:

MySQL-Server starten

SQL-Skript aus dem Ordner /db ausführen

Anwendung starten:

mvn spring-boot:run

Die Anwendung ist unter http://localhost:8080 erreichbar.

Technologien

Frontend: JSF (JavaServer Faces), PrimeFaces

Backend: Java EE (CDI, JPA)

Datenbank: MySQL

Persistenz-Provider: Hibernate

Datenbankstruktur

Die Datenbank enthält folgende Tabellen:

Geisternetz (ID, Standort, Größe, Status)

Person (ID, Name, Telefonnummer, Rolle)

Bergung (Netz_ID, Person_ID, Status)

Systemarchitektur

Die Architektur folgt dem MVC-Muster (Model-View-Controller) und beinhaltet:

Model: JPA-Entitäten zur Abbildung der Datenbankstrukturen.

View: JSF-Seiten zur Benutzerinteraktion.

Controller: CDI-Beans zur Geschäftslogik.

Mitwirkende

[Dein Name] (Projektleitung und Entwicklung)

[Teammitglied] (Backend-Entwicklung)

[Teammitglied] (Frontend-Entwicklung)

Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Weitere Informationen findest du in der Datei LICENSE.md.

Kontakt

Bei Fragen oder Anregungen wenden Sie sich an:

E-Mail: support@ghostnetfishing.com

GitHub-Issue-Tracker: https://github.com/yourusername/ghost-net-fishing/issues

