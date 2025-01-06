# Tabla de Contenidos

1. [Introducción](#introducción)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Descripción de Archivos Clave](#descripción-de-archivos-clave)
4. [Configuración](#configuración)
5. [Ejecución](#ejecución)
6. [Pruebas](#pruebas)

## Introducción

Este proyecto es una implementación de un sistema de autenticación utilizando OAuth con proveedores como Google y GitHub. Está construido utilizando Java y Open Liberty.

## Estructura del Proyecto

```plaintext
src
├── main
│   ├── java
│   │   └── io
│   │       └── openliberty
│   │           ├── guides
│   │           │   ├── rest
│   │           │   │   ├── AuthResource.java         # Clase principal del endpoint REST
│   │           │   │   ├── HealthResource.java       # Otros endpoints de estado o utilidades
│   │           └── oauth
│   │           │   ├── OAuthProvider.java            # Interfaz común para los proveedores OAuth
│   │           │   ├── GoogleOAuthProvider.java      # Implementación para Google
│   │           │   ├── GitHubOAuthProvider.java      # Implementación para GitHub
│   │           │   └── OAuthService.java             # Lógica común para manejar OAuth
│   │           └── utils
│   │               ├── JsonUtils.java                # Clases utilitarias para manejo de JSON
│   │               └── HttpUtils.java                # Clases utilitarias para manejo de HTTP
│   ├── resources
│   │   ├── META-INF
│   │   │   └── microprofile-config.properties        # Configuración de MicroProfile
│   │   └── application.yaml                          # Configuración adicional (opcional)
│   ├── webapp
│   │   ├── index.html                                # Página principal para pruebas (opcional)
│   │   ├── error.html                                # Página de error
│   │   └── css                                       # Archivos CSS (opcional)
│   └── liberty
│       └── config
│           ├── server.xml                            # Configuración del servidor Open Liberty
│           └── bootstrap.properties                  # Configuración inicial del servidor
└── test
    ├── java
    │   └── io
    │       └── openliberty
    │           └── guides
    │               ├── AuthResourceTest.java         # Pruebas para AuthResource
    │               └── OAuthServiceTest.java         # Pruebas para OAuthService
    └── resources
        └── test-config.properties                    # Configuración específica para pruebas
```

## Descripción de Archivos Clave

- **AuthResource.java**: Clase principal del endpoint REST para autenticación.
- **HealthResource.java**: Endpoints adicionales para estado y utilidades.
- **OAuthProvider.java**: Interfaz común para los proveedores OAuth.
- **GoogleOAuthProvider.java**: Implementación del proveedor OAuth para Google.
- **GitHubOAuthProvider.java**: Implementación del proveedor OAuth para GitHub.
- **OAuthService.java**: Lógica común para manejar OAuth.
- **JsonUtils.java**: Utilidades para manejo de JSON.
- **HttpUtils.java**: Utilidades para manejo de HTTP.
- **microprofile-config.properties**: Configuración de MicroProfile.
- **application.yaml**: Configuración adicional opcional.
- **server.xml**: Configuración del servidor Open Liberty.
- **bootstrap.properties**: Configuración inicial del servidor.
- **AuthResourceTest.java**: Pruebas unitarias para `AuthResource`.
- **OAuthServiceTest.java**: Pruebas unitarias para `OAuthService`.
- **test-config.properties**: Configuración específica para pruebas.

## Configuración

Para configurar el proyecto, asegúrate de ajustar los archivos de configuración según tus necesidades específicas, especialmente los archivos de configuración de OAuth y del servidor.

## Ejecución

Para ejecutar el proyecto, utiliza los comandos de Maven o Gradle según tu configuración de build. Asegúrate de tener configurado el servidor Open Liberty correctamente.

## Pruebas

Las pruebas unitarias están ubicadas en el directorio `test`. Puedes ejecutarlas utilizando tu herramienta de build preferida.
