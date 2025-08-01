# Spring AI Boilerplate Template

A comprehensive Spring Boot template project for building AI-powered applications using Spring AI 1.0. This template provides a robust foundation for developing enterprise-grade applications that leverage artificial intelligence capabilities including chat interactions, vector storage, document processing, and persistent chat memory.

## 🚀 Features

- **OpenAI Integration** - Chat completions and embeddings using GPT-4o-mini and text-embedding-3-small
- **Vector Storage** - PostgreSQL with PGVector extension for semantic search and RAG applications
- **Chat Memory** - JDBC-based persistent chat memory for conversation continuity
- **Document Processing** - PDF document reader for knowledge extraction
- **Vector Store Advisors** - Enhanced AI responses with context-aware recommendations
- **API Documentation** - Interactive Swagger UI for API exploration
- **Health Monitoring** - Spring Boot Actuator endpoints for application monitoring
- **Development Tools** - Hot reload with Spring Boot DevTools

## 🛠 Technology Stack

### Core Framework
- **Spring Boot 3.4.0** - Main application framework
- **Spring AI 1.0.0** - AI integration and orchestration
- **Java 17** - Programming language

### AI & ML
- **OpenAI GPT-4o-mini** - Chat completions
- **OpenAI text-embedding-3-small** - Text embeddings
- **Spring AI Chat Client** - Simplified chat interactions
- **Spring AI Vector Store** - Vector similarity search

### Database & Storage
- **PostgreSQL** - Primary database
- **PGVector Extension** - Vector similarity search
- **Spring Data JPA** - Data access layer
- **Hibernate** - ORM framework

### Documentation & Monitoring
- **SpringDoc OpenAPI 3** - API documentation
- **Swagger UI** - Interactive API explorer
- **Spring Boot Actuator** - Application monitoring

### Development Tools
- **Lombok** - Boilerplate code reduction
- **Spring Boot DevTools** - Development productivity
- **Maven** - Build and dependency management

## 📁 Project Structure

```
spring-ai-template/
├── src/
│   ├── main/
│   │   ├── java/com/sbansal/ai/template/
│   │   │   ├── SpringAiTemplateApplication.java    # Main application class
│   │   │   ├── config/
│   │   │   │   └── SpringAiTemplateConfig.java     # Configuration beans
│   │   │   └── web/
│   │   │       └── ChatController.java             # REST API endpoints
│   │   └── resources/
│   │       └── application.properties              # Application configuration
│   └── test/
│       └── java/com/sbansal/ai/template/
│           └── SpringAiTemplateApplicationTests.java
├── pom.xml                                         # Maven dependencies
└── README.md
```

## 🔧 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **PostgreSQL 12+** with PGVector extension
- **OpenAI API Key**

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd spring-ai-template
```

### 2. Database Setup

#### Option A: Docker (Recommended)
```bash
# Run PostgreSQL with PGVector extension
docker run --name postgres-ai \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=saranshbansal \
  -p 5432:5432 \
  -d pgvector/pgvector:pg16
```

#### Option B: Local PostgreSQL
```bash
# Install PostgreSQL and PGVector (macOS with Homebrew)
brew install postgresql@15 pgvector
brew services start postgresql@15

# Create database and enable vector extension
createdb saranshbansal
psql saranshbansal -c "CREATE EXTENSION IF NOT EXISTS vector;"
```

### 3. Create Vector Store Table
```sql
-- Connect to your database and run:
CREATE TABLE IF NOT EXISTS vector_store (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT,
    metadata JSONB,
    embedding vector(1536)
);

-- Create index for better performance
CREATE INDEX IF NOT EXISTS vector_store_embedding_idx 
ON vector_store USING hnsw (embedding vector_cosine_ops);
```

### 4. Environment Variables
```bash
# Set required environment variables
export OPEN_API_KEY=your_openai_api_key_here
export DB_USERNAME=postgres
export DB_PASSWORD=password
```

### 5. Run the Application
```bash
# Using Maven
./mvnw spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/spring-ai-template-0.0.1-SNAPSHOT.jar
```

## 📋 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# OpenAI Configuration
spring.ai.openai.api-key=${OPEN_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini
spring.ai.openai.embedding.options.model=text-embedding-3-small

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/saranshbansal
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# Vector Store Configuration
spring.ai.vectorstore.pgvector.index-type=HNSW
spring.ai.vectorstore.pgvector.distance-type=COSINE_DISTANCE
spring.ai.vectorstore.pgvector.dimensions=1536

# Chat Memory Configuration
spring.ai.chat.memory.enabled=true
spring.ai.chat.memory.repository.type=jdbc
```

## 🔌 API Endpoints

### Chat API
- **GET** `/chat/ask?message={your_message}` - Send a message to the AI chat

#### Example Usage
```bash
# Basic chat interaction
curl "http://localhost:8080/chat/ask?message=Hello, how are you?"

# Default joke request (no message parameter)
curl "http://localhost:8080/chat/ask"
```

### Monitoring Endpoints
- **GET** `/actuator/health` - Application health status
- **GET** `/actuator/info` - Application information
- **GET** `/actuator/metrics` - Application metrics

### API Documentation
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

## 🏗 Architecture Components

### ChatController
- REST endpoint for AI chat interactions
- Uses Spring AI ChatClient for OpenAI integration
- Supports parameterized queries with default fallback

## Technologies Used

- Spring Boot
- PostgreSQL
- Spring AI
- Other technologies...

## Setup Instructions

1. Clone the repository
2. Configure database properties in `application.properties`
3. Set up environment variables:
    - DB_USERNAME
    - DB_PASSWORD
    - OPENAI_API_KEY
4. Run the application

## API Documentation

API documentation is available at `/swagger-ui.html` when the application is running.
