# Projeto Netflix Fiap - 76AOJ

## Index
* [About the project](#about)
* [Solution Architecture Design](#architecture)
* [How to run](#run)


## About the project <a name="about"></a> :link:
Esse projeto consiste no desenvolvimento de algumas funcionalidades do ecossistema da Netflix onde utilizando uma implementa��o baseada em Microservi�os utlizando o `Spring Boot (2.3.7.RELEASE)` como parte da solu��o, pois o mesmo tem tecnologias e solu��es j� prontas para serem implementadas.

#### Dominio dos servi�os
N�s dividimos o dominio em 7 projetos: `(netflix-user, netflix-authorization, netflix-category, netflix-movies, netflix-series, netflix-likes, netflix-help-desk)`, portanto, em nossas APIs usamos algumas tecnologias e solu��es para construir uma arquitetura s�lida, segura, rastre�vel e escal�vel.

* **Spring Cloud Netflix Eureka:** Usamos o `Netflix Eureka` como uma solu��o de` Service Discovery` -  que consiste em um dos principais princ�pios da arquitetura baseada em microservices e o Eureka � o Service Discovery do Netflix que � usado no Server e no Cliente.


* **Spring Cloud Config:** � um micro-servi�o onde outros micro-servi�os consultam as propriedades da pr�pria aplica��o.


* **Spring Cloud OpenFeign:** Foi utilizado para fazer chamadas entre microsservi�os de forma simples para seus clientes. Com ele, tamb�m podemos usar o Client Side Load Balancer porque o Feign � integrado ao Ribbon, que por sua vez tamb�m � integrado ao Eureka.


* **Spring Cloud Sleuth:** Respons�vel por implementar uma solu��o de rastreamento distribu�do, que nos ajuda a rastrear solicita��es entre microsservi�os por meio de um ID de correla��o, para que possamos rastrear todo o fluxo de uma solicita��o que passa v�rios microsservi�os.


* **Netflix Hystrix:** Ele disponibiliza mecanismos para controle de falhas e de lentid�o nas chamadas para os servi�os, que tornam a aplica��o mais resiliente � interrup��o de chamadas. Evita, assim, a propaga��o de falhas para os servi�os.


* **Intelligent Routing (Zuul Proxy):** � uma solu��o de roteamento din�mico que possibilita monitoramento, resili�ncia e seguran�a para aplica��es.


* **Spring Cloud OAuth (OAuth2) - Authentication and authorization between microservices:** Configuramos toda a seguran�a com Spring Security e `Spring Cloud OAuth` e conectamos atrav�s de adaptadores de seguran�a padr�o spring com` OAuth2`. O nome de usu�rio e senha est�o na mem�ria para facilitar o teste.

#### Solution Architecture Design <a name="architecture"></a> :link:
![architecture](readme/img/architecture-design.png)


## How to run <a name="run"></a> :wrench:
Siga as instru��es abaixo para construir e executar o projeto de forma simples e f�cil, mas fique � vontade para execut�-lo da maneira que desejar.

**Tecnologias:**

* Java 11
* Maven
* PostgreSQL (Recommendo utilizar o Docker)
* Redis
* Zookeeper/Kafka (Recommendo utilizar o Docker)
* Postman for testing
* Zipkin (Recommendo utilizar o Docker)

**Executando a infra da aplica��o com Docker (Recomendando):**

- [Download Docker](https://www.docker.com/products/docker-desktop)

Execute o camando abaixo no terminal de sua preferencia:

```
Iniciando as aplica��es: `docker-compose up -d --build`   
Parando aplica��es: `docker-compose down`   
Visualizando logs: `docker-compose logs -f`   
```

**Build and Run**

Execute os microsservi�os seguindo a ordem abaixo:

1. [netflix-config-server](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-config-server)
2. [netflix-eureka-server](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-eureka-server)
3. [netflix-user](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-user)
4. [netflix-authorization](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-authorization)
5. [netflix-authorization](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-authorization)
6. [netflix-category](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-category)
7. [netflix-likes](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-likes)
8. [netflix-movies](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-movies)
9. [netflix-series](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-series)
10. [netflix-help-desk](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-help-desk)
11. [netflix-netflix-gateway](https://github.com/sironAzevedo/fiap-76aoj-netflix/tree/main/netflix-netflix-gateway)

Fique � vontade para usar seu IDE favorita

Acessar o dashboard do Eureka Server no navegador: `http://localhost:8761/`   
Acessar o dashboard do zipkin no navegador: `http://localhost:9411/`   
Acessando o kafka pela ferramenta [Conductor](https://www.conduktor.io/download/): `http://localhost:9092/`   

url-netflix-config-server: `http://localhost:8888/`   
url-netflix-eureka-server: `http://localhost:8761/eureka`   
url-netflix-netflix-gateway: `http://localhost:8765/`   

#### Acessando as aplica��es pelo Postman:

* **Login**

URL: `{{url-netflix-netflix-gateway}}/netflix-authorization/oauth/token`

```

Login Cliente:
Usuario: teste_create_silva@fiap.com
Senha: 123
grant_type: password

Login Aplica��o:
Username: fiapappnetflix
Password: fiapappnetflix76aoj

```

![authorization](readme/img/postman-login-tela-authorization.png)
![body](readme/img/postman-login-body.png)

**OBS.:** Copiar o conteudo do campo `token` gerado no login e colar na aba de Authorization para realizar as requisi��es abaixo:   
![listar-filme](readme/img/postman-listar-filmes.png)

* **Exemplos de requisi��o:**

Listar Categorias: `{{url-netflix-netflix-gateway}}/netflix-category/category?page=0&size=5`   
Listar Movies: `{{url-netflix-netflix-gateway}}/netflix-movies/movies?page=0&size=10`    
Like Movie: `{{url-netflix-netflix-gateway}}/netflix-likes/likes/movie?email=teste_create_silva@email.com&movie=1&like=yes`     


* **Acessando a base de dados:**

```
db-netflix-user:
	HOST: localhost
	DATABASE: db_netflix_user
	PORT: 5432
OBS.: Caso j� tenha o postgres instalado na maquina, por favor, execute o stop do mesmo para n�o dar conflito.
	
db-netflix-category:
	HOST: localhost
	DATABASE: db_netflix_category
	PORT: 5433
	
db-netflix-movies:
	HOST: localhost
	DATABASE: db_netflix_movies
	PORT: 5434

db-netflix-series:
	HOST: localhost
	DATABASE: db_netflix_series
	PORT: 5435

db-netflix-help-desk:
	HOST: localhost
	DATABASE: db_netflix_help_desk
	PORT: 5436
```



















































































