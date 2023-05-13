SET FOREIGN_KEY_CHECKS=0
;

CREATE TABLE `journey`
(
    `departure`                     DATETIME NOT NULL COMMENT 'Date and time of departure',
    `return`                        DATETIME NOT NULL COMMENT 'Date and time of return',
    `departure_station_id`          INT(11) NOT NULL COMMENT 'Id of departure station',
    `departure_station_name`        VARCHAR(50) NOT NULL COMMENT 'Name of departure station',
    `return_station_id`             INT(11) NOT NULL COMMENT 'Id of return station',
    `return_station_name`           VARCHAR(50) NOT NULL COMMENT 'Name of return station',
    `distance_covered_in_meters`    INT(11) NOT NULL COMMENT 'Distance covered in meters',
    `duration_in_seconds`           INT(11) NOT NULL COMMENT 'Duration in seconds'
) ENGINE = InnoDB DEFAULT CHARSET = utf8
;

CREATE TABLE `station`
(
    `fid`           INT(11) NOT NULL COMMENT 'ID',
    `id`            INT(11) NOT NULL COMMENT 'Station ID',
    `nimi`          VARCHAR(50) NOT NULL COMMENT 'Station name in Finnish',
    `namn`          VARCHAR(50) NOT NULL COMMENT 'Station name in Swedish',
    `name`          VARCHAR(50) NOT NULL COMMENT 'Station name',
    `osoite`        VARCHAR(50) NOT NULL COMMENT 'Address in Swedish',
    `adress`        VARCHAR(50) NOT NULL COMMENT 'Address in Swedish',
    `kaupunki`      VARCHAR(50) NOT NULL COMMENT 'City name in Finnish',
    `stad`          VARCHAR(50) NOT NULL COMMENT 'City name in Swedish',
    `operaattor`    VARCHAR(50) NOT NULL COMMENT 'Operator',
    `kapasiteet`    INT(11) NOT NULL COMMENT 'Bicycle capacity of station',
    `x`             DOUBLE NOT NULL COMMENT 'X co-ordinate',
    `y`             DOUBLE NOT NULL COMMENT 'Y co-ordinate'
) ENGINE = InnoDB DEFAULT CHARSET = utf8
;


SET FOREIGN_KEY_CHECKS=1
;