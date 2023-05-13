default: build

init:
	docker-compose up -d mariadb
	sleep 5
	docker exec -i citybike-mariadb sh < config/mariadb-init.sh
	mvn clean
	mvn install -DskipTests -T 1C
	docker-compose down
	docker-compose up -d

build:
	mvn install -DskipTests -T 1C

