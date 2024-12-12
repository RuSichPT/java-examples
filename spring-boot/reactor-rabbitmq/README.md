# Reactor RabbitMQ

### Open API
http://localhost:8080/swagger-ui/index.html

### RabbitMQ
https://registry.hub.docker.com/_/rabbitmq/

    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

    docker stop rabbitmq

http://localhost:15672/  
user = guest  
password = guest
