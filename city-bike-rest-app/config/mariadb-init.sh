#!/bin/sh
mysql -e "SET PASSWORD FOR 'root'@'%' = ''"
mysql -e "CREATE USER city_bike_test"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO city_bike_test"



