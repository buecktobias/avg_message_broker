start cmd /k docker-compose exec java_hardware_order java Main start_listening
start cmd /k docker-compose exec javascript_software_order node main.js listen
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung1
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung2
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung1
docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung2
docker-compose exec javascript_website node main.js sendSoftwareOrder
docker-compose exec javascript_website node main.js sendHardwareOrder
