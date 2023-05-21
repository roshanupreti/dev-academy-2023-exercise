#!/bin/sh
mysql -e "SET PASSWORD FOR 'root'@'localhost' = ''"
mysql -e "CREATE USER city_bike_test"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO city_bike_test"

