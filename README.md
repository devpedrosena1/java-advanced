# Drone Mission Control API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge&logo=apachemaven&logoColor=white)
![FIAP](https://img.shields.io/badge/FIAP-ED145B?style=for-the-badge)

---

## 💡 Sobre o Projeto

O **Drone Mission Control API** é uma aplicação desenvolvida em **Spring Boot** que implementa um sistema de gerenciamento de **drones e missões**.  

A API oferece as operações **CRUD básicas** (cadastrar, consultar, atualizar e remover drones e missões), além de **endpoints não-CRUD inspirados em cenários reais de uso** — como listagem de missões futuras, geração de relatórios de bateria, ranking de drones mais utilizados e busca de missões por localização.  

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (banco de dados local para testes)
- **Maven** (gerenciador de dependências)
- **Springdoc OpenAPI** (documentação Swagger UI)

---

## 🗂️ Entidades do Sistema

### **Drone**
Representa um drone cadastrado no sistema, que pode estar associado a várias missões (**relação 1:N**).  

**Atributos:**
- `id` *(UUID)* → Identificador único do drone, gerado automaticamente  
- `model` *(String, máx. 100)* → Modelo do drone (ex.: *Drone Teste*)  
- `batteryCapacity` *(int)* → Capacidade da bateria em minutos  
- `status` *(String, máx. 20)* → Situação atual (`ativo`, `manutenção`, `fora de operação`)  
- `missions` *(List<Mission>)* → Lista de missões atribuídas ao drone  

---

### **Missão**
Representa uma missão atribuída a um drone específico.

**Atributos:**
- `id` *(UUID)* → Identificador único da missão, gerado automaticamente
- `description` *(String, máx. 100)* → Texto livre com a descrição (ex.: *Inspeção de linha de transmissão*)
- `location` *(String, máx. 50)* → Localização da missão (cidade ou coordenadas)
- `scheduledDate` *(LocalDate)* → Data prevista para execução da missão
- `estimatedBatteryUsage` *(int)* → Consumo de bateria estimado (em minutos)
- `droneId` *(Drone)* → Drone responsável pela missão (chave estrangeira) 

---

## 📌 Endpoints

### **Drones** (`/api/v1/drones`)

- `POST /api/drone` → Insere um novo drone
- `GET /api/drone/{id}` → Busca drone por ID
- `PUT /api/drone/{id}` → Atualiza um drone (objeto inteiro) por ID
- `PATCH /api/drone/{id}` → Atualiza parcialmente um drone por ID
- `DELETE /api/drone` → Remove o objeto drone por ID
- `DELETE /api/drone/removeObject` → Remove objeto drone inteiro
- `GET /api/drone/{id}/baterry-report` → Retorna a média de bateria de drone por ID
- `GET /api/drone/{id}/ranking` → Retorna o ranking de drones por missão

### **Missões** (`/api/v1/missions`)

- `GET /api/mission` → Lista todas as missões
- `GET /api/mission/{id}` → Busca missão por ID
- `GET /api/mission/mission-drone/{droneId}` → Busca missões ligadas a um drone específico
- `POST /api/mission` → Cadastra uma nova missão

---

## 📋 Exemplos de Requisições (cURL)

### **Cadastrar Drones**

```bash
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "model": "string",
  "batteryCapacity": 0,
  "status": "string"
}
```

---

### **Cadastrar Missões**

```bash
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "description": "string",
  "location": "string",
  "scheduledDate": "2025-09-27",
  "droneId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "estimatedBatteryUsage": 0
}
```

---


## 🧐 Justificativa para uso de GET nos Endpoints Não-CRUD

Os endpoints não-CRUD da API utilizam o método **HTTP GET** porque realizam apenas **consultas a dados já existentes** — como a listagem de missões futuras de um drone, geração de relatórios de uso de bateria, ranking de drones mais utilizados ou busca por localização.
Como não envolvem **criação, alteração ou exclusão de recursos**, não se justificaria o uso de métodos como `POST`, `PUT` ou `DELETE`.

---

## 🚀 Como Executar Localmente

### **Pré-requisitos**

- [Java 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
- [Maven 3.9+](https://maven.apache.org/download.cgi)  

### **Instalação**


1. **Clonar o repositório**  
```bash
git clone https://github.com/devpedrosena1/java-advanced/Project-Mission-Drone.git
```

2. **Entrar na pasta do projeto**  
```bash
cd Project-Mission-Drone
```

3. **Compilar o projeto**  
```bash
mvn clean install
```

### **Execução**

**Executar a aplicação**  
```bash
mvn spring-boot:run
```

O projeto iniciará em:  
👉 [http://localhost:8080](http://localhost:8080)

A documentação Swagger estará disponível em:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📐 Especificações Técnicas

- Arquitetura organizada em **camadas**:  
  - `domainmodel` → entidades e repositórios  
  - `service` → regras de negócio  
  - `presentation.controllers` → endpoints REST  
  - `infrastructure` → configuração do Swagger e handlers globais  

- Repositórios customizados (`DroneRepositoryCustom`, `DroneRepositoryImpl`, etc.) implementam consultas avançadas com QueryDSL.
- Uso de **DTOs** para transporte de dados (`DroneDTO`, `MissionDTO`, `DroneRankingDTO`)  
- Tratamento centralizado de exceções com `GlobalExceptionHandler`

---

## 👥 Contribuidores

- [**@devpedrosena1**](https://github.com/devpedrosena1) — Desenvolvimento Backend
