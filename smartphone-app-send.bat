echo "hii"
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung1
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung2
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung1
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung2
