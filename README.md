# About
Projekt zum Testen von Message Brokern. Verwendung von Apache ActiveMQ.
# How To Start Docker Containers
Downloaden dieses Github Repositories. Daraufhin im Ordner den Befehl
```
docker-compose up --build --abort-on-container-exit
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