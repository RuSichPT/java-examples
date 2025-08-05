# Grafana Loki Tempo

### [Logging in Spring Boot With Loki](https://www.baeldung.com/spring-boot-loki-grafana-logging)

### [Quick start for Tempo](https://grafana.com/docs/tempo/latest/getting-started/docker-example/)

### [OpenTelemetry Setup in Spring Boot Application](https://www.baeldung.com/spring-boot-opentelemetry-setup#bd-conclusion)

### [MDC Instrumentation for Logback version 1.0 and higher](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/logback/logback-mdc-1.0/library)

    docker compose -f ./docker/grafana-loki-tempo/docker-compose.yaml up -d 

## Loki
Чтобы logback передал в Loki логи необходимо создать переменную среды  
LOKI=http://localhost:3100

## Open Telemetry
Чтобы работала трасировка необходимо создать переменные среды для агента
OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4318;  
OTEL_LOGS_EXPORTER=none;  
OTEL_METRICS_EXPORTER=none;  
OTEL_SERVICE_NAME=test-app  

Запуск агента 

    -javaagent:./agents/opentelemetry-javaagent.jar

### Open API
http://localhost:8080/swagger-ui/index.html