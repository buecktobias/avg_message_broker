# About
Projekt zum Testen von Message Brokern. Verwendung von Apache ActiveMQ.
# How To Start Docker Containers
Downloaden dieses Github Repositories. Daraufhin im Ordner den Befehl
```
docker-compose up --build
```
ausführen.


# Smartphone App

## Hardware Bestellung Senden
```
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung1
```
```
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung2
```

## Software Bestellung Senden
```
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung1
```
```
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung2
```

# Webseite
## Hardware Bestellung Senden
```
docker-compose exec javascript_website node main.js SoftwareBestellung
```


## Software Bestellung Senden
```
docker-compose exec javascript_website node main.js HardwareBestellung
```

# Coding Conventions
## Allgemein:
- Methoden-, Attribut- und Variablenamen sollten in **camelCase** sein und **aussagekräftig**.
  Keine Namen wie **con** oder **num** stattdessen **messageBrokerHTTPConnection** oder **activemqConnection**
- Keinen doppelten Code. Doppelter Code sollte in andere Methoden ausgelagert werden
- Methoden sollten nicht länger als 10 Zeilen sein

## Java:
- Wenn der Typ einer Variable offensichtlich ist, sollte dieser weggelassen werden und stattdessen var oder final var genutzt werden.
 beispiel `URL activemqSoftwareOrderQueueURL = new URL("...");`
 sollte durch `var activemqSoftwareOrderQueueURL = new URL("...");` ersetzt werden 

## Javascript:
- Keine Vergleiche mit **==** stattdessen **===**
- Kein **var** verwenden
- Wenn möglich Konstanten **const** statt **let**  verwenden

# Scenarios
## Scenario 1 - Zuerst werden beide Consumer gestartet dann senden beide Producer
### Beschreibung
Zuerst werden beide Consumer gestartet dann senden beide Producer.
### Ausführung
In den AvG Ordner wechseln
Zuerst mit `docker compose up --build` die Container starten. Warten bis alle Container gestartet sind (dauert ca. 40 Sekunden).
Danach `.\scenario_consumers_started_both_producers_sending.bat` ausführen.
Es öffnen sich zwei Fenster, das eine zeigt den Output vom Java Hardware Consumer und der andere vom Javascript Software Consumer.

## Scenario 2 - Zuerst senden beide Producer dann werden die Consumer gestartet
### Beschreibung
Zuerst senden beide Producer ihre Bestellungen, dann werden die Consumer gestartet.
### Ausführung
In den AvG Ordner wechseln
Zuerst mit `docker-compose -f docker-compose.yml up javascript_website java_smartphone_app` die beiden Producer Container starten.
Danach `scenario_consumers_started_after_both_producers_sending` ausführen.
Anschließend 'docker-compose -f docker-compose.yml up java_hardware_order javascript_software_order' die beiden Consumer Container starten.
