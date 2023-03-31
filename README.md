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
