start cmd /k docker-compose exec java_hardware_order java Main start_listening
start cmd /k docker-compose exec javascript_software_order node main.js listen
docker-compose stop activemq
docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung1
