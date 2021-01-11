# Projeto Netflix Fiap - 69AOJ

## Resumo

Portas:

```

netflix-mysql:3306
netflix-kafka:9092
netflix-prometheus:9090 
netflix-zipkin:9411
netflix-discovery-server:8761
netflix-gateway:9091
netflix-configuration-server:9093

netflix-catalog:8080
netflix-help-desk:8081
netflix-user:8082
netflix-order:8083

```

## Atualizando submodules

```
git submodule update --recursive --remote
```

---

## Build projetos

### Discovery Server

cd netflix-discovery-server

Build Project
mvn package

### Gateway

cd netflix-gateway

Build Project
mvn package

### User

cd netflix-user

Build Project
mvn package

---

## Deploy

Iniciando aplicações:

```
sudo docker-compose up -d --build
```

Parando aplicações:

```
sudo docker-compose down
```

Visualizando logs:

```
sudo docker-compose logs -f
```

---

## Iniciar Infra (kafka, zipkin e discovery)

Iniciando aplicações:

```
sudo docker-compose -f docker-compose-infra.yml up -d --build
```

Parando aplicações:

```
sudo docker-compose down
```