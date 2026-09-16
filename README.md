# IntelliDoc AI API

Backend desarrollado para la plataforma **IntelliDoc AI**, un sistema inteligente de procesamiento documental basado en Inteligencia Artificial y tecnologías Cloud.

El proyecto hace parte del desarrollo del proyecto de prácticas empresariales de Ingeniería de Software y tiene como objetivo automatizar el procesamiento de documentos mediante una arquitectura escalable basada en Azure Cloud y Clean Architecture.

---

# Arquitectura

La solución implementa una arquitectura híbrida compuesta por:

- Clean Architecture
- Arquitectura Serverless
- API REST
- Azure Functions
- Azure Blob Storage
- Azure OpenAI

La aplicación cliente será desarrollada utilizando el patrón MVC y consumirá esta API REST.

---

# Tecnologías

- Java 21
- Spring Boot 3
- Maven
- Spring Web
- Spring Validation
- Spring Data JPA
- PostgreSQL
- Azure Functions
- Azure Blob Storage
- Azure OpenAI
- Swagger / OpenAPI

---

# Estructura del Proyecto

```
com.intellidoc.api

├── application
│   ├── dto
│   ├── mapper
│   └── usecase
│
├── domain
│   ├── model
│   ├── ports
│   └── service
│
├── infrastructure
│   ├── config
│   ├── persistence
│   ├── repository
│   └── external
│
├── web
│   ├── controller
│   ├── request
│   └── response
│
└── shared
    ├── constants
    ├── enums
    ├── exception
    ├── mapper
    ├── util
    └── validation
```

---

# Roadmap

## Sprint 0

- [x] Creación del proyecto
- [ ] Configuración inicial
- [ ] Clean Architecture
- [ ] Swagger
- [ ] Health Check

## Sprint 1

- Gestión de documentos
- Carga de archivos
- Validación documental

## Sprint 2

- Integración Azure Blob Storage
- Azure Functions

## Sprint 3

- Procesamiento mediante Inteligencia Artificial
- Azure OpenAI

## Sprint 4

- Seguridad
- JWT
- Gestión de usuarios

## Sprint 5

- Integración con la aplicación MVC
- Despliegue

---

# Estado del Proyecto

En desarrollo.

---

# Autor

Proyecto desarrollado por **Damián Santamaría** como parte del proyecto de prácticas empresariales del programa de Ingeniería de Software.
