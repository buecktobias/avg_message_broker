#!/bin/bash
echo "hii"
$hardwareBestellung1=$(docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung1)
$hardwareBestellung2=$(docker-compose exec java_smartphone_app java Main.java sendeHardwareBestellung2)
$softwareBestellung1=$(docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung1)
$softwareBestellung2=$(docker-compose exec java_smartphone_app java Main.java sendeSoftwareBestellung2)
echo $hardwareBestellung1
echo $hardwareBestellung2
echo $softwareBestellung1
echo $softwareBestellung2