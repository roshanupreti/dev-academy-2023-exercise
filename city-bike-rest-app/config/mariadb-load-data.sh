#!/bin/sh
# Load Journey Data
for journey_data in /journey/*.csv
do
    mysql -e "use client" -e "
    LOAD DATA INFILE '$journey_data'
    INTO TABLE journey
    CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    OPTIONALLY ENCLOSED BY '\"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS
    (departure, arrival, departure_station_id, departure_station_name, return_station_id, return_station_name, @distance_covered,duration)
    SET distance_covered = IF(@distance_covered='' OR @distance_covered='\r' OR @distance_covered='\n' OR @distance_covered='\r\n' ,NULL,@distance_covered)"
echo "Done: '$journey_data' at $(date)"
done


# Load Station Data
for station_data in /station/*.csv
do
    mysql -e "use client" -e "
    LOAD DATA INFILE '$station_data'
    INTO TABLE station
    CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    OPTIONALLY ENCLOSED BY '\"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS"
echo "Done: '$station_data' at $(date)"
done

# Filter out journeys that lasted for less than ten seconds or covered a distance shorter than ten meters
mysql -e "use client;DELETE from journey WHERE duration < 10 OR distance_covered < 10"


