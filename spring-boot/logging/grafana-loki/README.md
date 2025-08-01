# Grafana Loki

### [Logging in Spring Boot With Loki](https://www.baeldung.com/spring-boot-loki-grafana-logging)

    docker compose -f ./docker/grafana-loki/docker-compose.yaml up -d 

Чтобы logback передал в Loki логи необходимо создать переменную среды
LOKI=http://localhost:3100