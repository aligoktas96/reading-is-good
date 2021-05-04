# Reading is good Case
---------------

## 🔨 Environments & Dependencies
```
------------------------------
- JDK 11
- Maven
- Docker Engine
------------------------------
- Spring Boot - 2.3.9.RELEASE
    - starter-web
    - starter-actuator
    - spring-boot-starter-data-jpa

- Spring Cloud - Hoxton.SR10
    - config-server
    - config-client
    - netflix-eureka server
    - netflix-eureka client
    - netflix-zuul
    - apache kafka
    - openfeign

- lombok 

- mysql-connector-java 


------------------------------
```

## 📦 Modules
##### 1. api-gateway-service - `Netflix Zuul`
    - This module acts as a proxy server. 
    - It provides routing to services (or different instances of the same service) registered with the Netflix Eureka service by load balancer.
    - Routing settings are defined in config-service > configs > api-gateway-service.yml file.
##### 2. config-service (contains all module configs - `Spring Cloud Config Server` )
    - It keeps the configs of the modules.
    - It has been created for manage configs from one service and at the runtime.
    - Modules access the config-service with the `spring.cloud.config` value in the bootstrap.yml file.
##### 3. discovery-service (Registration and Discovery service - `Netflix Eureka`)
    - It has been created to monitor the services.
    - Services access and register to this service with the `eureka.client.service-url` value in the properties file.
##### 4. product-service (Product microservice - `Spring Boot Application`)
    - This service is accessed through api-gateway-service.
    - Exception handling is managed with @ControllerAdvice service.
##### 5. order-service (Order microservice - `Spring Boot Application`)
    - This service is accessed through api-gateway-service.
    - Exception handling is managed with @ControllerAdvice service.
##### 6. customer-service (Customer microservice - `Spring Boot Application`)
    - This service is accessed through api-gateway-service.
    - Exception handling is managed with @ControllerAdvice service.


## 🚀 Run all instances
 - `docker-compose.yml` file has been created to run all modules.
 - `build-maven.sh` file is help to create the maven packages.
 - `build-docker.sh` file is help to create docker images.
 - `run.sh` file gets maven packages for all modules, creates the docker images, and starts the whole environment with docker-compose. 

 ##### To make it work;
  1. `chmod a+x *.sh`
  2. `./run.sh`

## 🔍 Service Access Points
- ##### Base API Endpoint : `http://localhost/api/`
- ##### Eureka Service : `http://localhost:8081/`
- ##### Api Gateway Endpoint : `http://localhost/api`
- ##### Product Create Endpoint : `http://localhost/api/product/create`
- ##### Product Get Endpoint : `http://localhost/api/product/{PRODUCT_ID}`
- ##### Customer Register Endpoint `http://localhost/api/customer/register`
- ##### Order Create Endpoint `http://localhost/api/order/create`
- ##### Order Get Endpoint `http://localhost/api/order/{ORDER_ID}`
- ##### Order Get By Customer Endpoint `http://localhost/api/order/customer/{CUSTOMER_ID}`

- ##### You can find all endpoint samples in the `sources/postman/Reading-is-good.postman_collection.json` postman collection file.

## ℹ️ Notes:

- I tried to provide consistency with the SAGA method.
- I tried to prevent the stock from falling to a negative number by marking the stock column in the product table as unsigned.
- I did all the tasks except Authentication. I could not do the authentication task as I could not complete it in time.
