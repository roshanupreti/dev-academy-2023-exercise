SET FOREIGN_KEY_CHECKS=0
;

CREATE TABLE `journey`
(
    `departure`                     DATETIME DEFAULT NULL COMMENT 'Date and time of departure',
    `arrival`                        DATETIME DEFAULT NULL COMMENT 'Date and time of return',
    `departure_station_id`          INT(11) DEFAULT NULL COMMENT 'Id of departure station',
    `departure_station_name`        VARCHAR(50) DEFAULT NULL COMMENT 'Name of departure station',
    `return_station_id`             INT(11) DEFAULT NULL COMMENT 'Id of return station',
    `return_station_name`           VARCHAR(50) DEFAULT NULL COMMENT 'Name of return station',
    `distance_covered`              INT(11) DEFAULT NULL COMMENT 'Distance covered in meters',
    `duration`                      INT(11) DEFAULT NULL COMMENT 'Duration in seconds'
) ENGINE = InnoDB DEFAULT CHARSET=latin1
;

CREATE TABLE `station`
(
    `fid`           INT(11) DEFAULT NULL COMMENT 'ID',
    `id`            INT(11) DEFAULT NULL COMMENT 'Station ID',
    `nimi`          VARCHAR(50) DEFAULT NULL COMMENT 'Station name in Finnish',
    `namn`          VARCHAR(50) DEFAULT NULL COMMENT 'Station name in Swedish',
    `name`          VARCHAR(50) DEFAULT NULL COMMENT 'Station name',
    `osoite`        VARCHAR(50) DEFAULT NULL COMMENT 'Address in Swedish',
    `adress`        VARCHAR(50) DEFAULT NULL COMMENT 'Address in Swedish',
    `kaupunki`      VARCHAR(50) DEFAULT NULL COMMENT 'City name in Finnish',
    `stad`          VARCHAR(50) DEFAULT NULL COMMENT 'City name in Swedish',
    `operaattor`    VARCHAR(50) DEFAULT NULL COMMENT 'Operator',
    `kapasiteet`    INT(11) DEFAULT NULL COMMENT 'Bicycle capacity of station',
    `x`             DOUBLE DEFAULT NULL COMMENT 'X co-ordinate',
    `y`             DOUBLE DEFAULT NULL COMMENT 'Y co-ordinate'
) ENGINE = InnoDB DEFAULT CHARSET=latin1
;


SET FOREIGN_KEY_CHECKS=1
;