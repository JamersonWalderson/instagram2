# Instagram2 - Clean Architecture Spring Boot

API REST desenvolvida com **Spring Boot 4.0.3** seguindo os princípios de **Clean Architecture**.

## 🏗️ Arquitetura

O projeto segue Clean Architecture com separação clara de responsabilidades:

```
instagram2/
├── domain/              # Regras de negócio e entidades
│   ├── model/          # Entidades do domínio
│   └── repository/     # Interfaces de repositório
├── usecase/            # Casos de uso (lógica de aplicação)
├── infrastructure/     # Implementações técnicas
│   └── persistence/    # Repositórios JPA
└── presentation/       # Controllers REST e DTOs
    ├── dto/           # Data Transfer Objects
    └── exception/     # Tratamento de exceções
```

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 4.0.3**
- **PostgreSQL 17**
- **Swagger/OpenAPI 3.0**
- **Lombok**
- **Gatling 3.9.5** (testes de performance)
- **Docker Compose**

## 📋 Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL (via Docker)

## 🔧 Configuração

### 1. Iniciar Banco de Dados

```bash
docker-compose up -d
```

### 2. Configurar application.properties

O arquivo `src/main/resources/application.properties` já está configurado para PostgreSQL local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estudo
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## ▶️ Executar Aplicação

```bash
# Compilar o projeto
./mvnw clean compile

# Executar aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

## 📚 Documentação da API

Acesse o Swagger UI em: **http://localhost:8080/swagger-ui.html**

### Endpoints Disponíveis

#### **POST /api/posts**
Criar um novo post

```json
{
  "title": "Meu primeiro post",
  "content": "Conteúdo do post..."
}
```

#### **GET /api/posts**
Listar todos os posts

```json
[
  {
    "id": 1,
    "title": "Meu primeiro post",
    "content": "Conteúdo do post..."
  }
]
```

## 🧪 Testes de Performance com Gatling

### Executar Testes de Carga

**Importante**: A aplicação deve estar rodando antes de executar os testes!

```bash
# Terminal 1 - Iniciar aplicação
./mvnw spring-boot:run

# Terminal 2 - Executar teste de performance
./mvnw gatling:test
```

### Configuração do Teste

O teste está configurado em `src/test/scala/gatling/simulations/BasicSimulation.scala`:

- **100 usuários** simultâneos
- **Ramp-up de 30 segundos**
- Testa endpoints: `GET /api/posts` e `POST /api/posts`

### Visualizar Resultados

Após a execução, o relatório HTML é gerado em:

```
target/gatling/results/basicsimulation-[timestamp]/index.html
```

Abra o arquivo no navegador para visualizar:
- Gráficos de tempo de resposta
- Taxa de requisições por segundo
- Percentis (p50, p75, p95, p99)
- Taxa de sucesso/erro

### Script Automatizado

Use o script para executar testes facilmente:

```bash
chmod +x run-performance-test.sh
./run-performance-test.sh
```

## 🐳 Docker

### Iniciar PostgreSQL

```bash
docker-compose up -d
```

### Parar PostgreSQL

```bash
docker-compose down
```

### Ver logs

```bash
docker-compose logs -f
```

## 📊 Validações

### Validação de Negócio (Domain)

```java
public void validate() {
    if (title == null || title.trim().isEmpty()) {
        throw new IllegalArgumentException("Title is required");
    }
    if (content == null || content.trim().isEmpty()) {
        throw new IllegalArgumentException("Content is required");
    }
}
```

### Validação de Input (DTO)

```java
@NotBlank(message = "Title is required")
@Size(max = 200, message = "Title cannot exceed 200 characters")
private String title;
```

## 🔍 Tratamento de Erros

Respostas de erro padronizadas:

```json
{
  "message": "Validation failed",
  "timestamp": "2026-03-16T22:00:00",
  "errors": {
    "title": "Title is required",
    "content": "Content is required"
  }
}
```

## 📖 Documentação Adicional

- **[CLEAN_ARCHITECTURE_GUIDE.md](CLEAN_ARCHITECTURE_GUIDE.md)** - Guia completo de implementação
- **[GATLING_SETUP.md](GATLING_SETUP.md)** - Configuração detalhada do Gatling

## 🎯 Próximos Passos

- [ ] Implementar paginação
- [ ] Adicionar autenticação/autorização
- [ ] Implementar cache com Redis
- [ ] Testes unitários e de integração
- [ ] CI/CD pipeline

## 📝 Commits

Este projeto segue regras de commit em **português brasileiro**. Consulte `.windsurf/rules/commit.md` para detalhes.

## 👨‍💻 Autor

Jamerson Walderson

## 📄 Licença

Este projeto é open source.
