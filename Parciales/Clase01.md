# Clase 01: Introducción a la Web como Plataforma

Bienvenidos a la primera clase de Backend. Hoy vamos a explorar cómo ha evolucionado la web y por qué es fundamental entender sus arquitecturas para desarrollar aplicaciones modernas y escalables.

---

## 1. Evolución de la Web y sus Arquitecturas

### 1.1. Modelo de Simple Nivel

En los inicios de la informática, las aplicaciones funcionaban en terminales bobas (dispositivos muy básicos que no tenían capacidad de procesamiento) conectadas a un mainframe (la computadora real que hacía todo el procesamiento). Todo el procesamiento (presentación, lógica de negocio y acceso a datos) ocurría en un solo lugar.

**Ventajas:**
- Fácil de mantener la consistencia de los datos.
- No requiere gestión del lado del cliente.

**Desventajas:**
- Difícil de modificar y mantener.
- Todo está entrelazado, lo que dificulta la reutilización.

**Explicación:**
Este modelo es como tener una sola máquina que hace todo. Si algo falla o necesita cambiarse, hay que modificar todo el sistema.

---

### 1.2. Modelo de Dos Niveles

Aquí aparecen los clientes gruesos (son computadoras o aplicaciones que realizan gran parte del procesamiento en forma local), que se conectan directamente a la base de datos. La presentación y la lógica de negocio se procesan en el cliente.

**Ventajas:**
- Independencia del producto de base de datos.

**Desventajas:**
- Si cambia el esquema de la base de datos, todos los clientes deben actualizarse.
- Difícil de escalar y mantener.
- Mucho tráfico de datos en la red.

**Explicación:**
Imagina que cada usuario tiene una copia de la aplicación y accede directamente a la base de datos. Si hay un cambio, todos deben actualizar su software.

---

### 1.3. Modelo de Tres Niveles (RPC)

RPC: Remote Procedure Call.

Se separa la presentación del negocio y el modelo de datos. El cliente solo maneja la presentación, mientras que la lógica de negocio y acceso a datos están en el servidor intermedio.

**Ventajas:**
- La lógica de negocio puede cambiar sin afectar la presentación.

**Desventajas:**
- El servidor intermedio se vuelve más complejo.
- El código no es completamente reusable.

**Explicación:**
Este modelo permite que los cambios en la lógica de negocio no afecten la interfaz del usuario, pero requiere servidores más robustos.

---

### 1.4. Tres Niveles con Objetos Remotos

La lógica de negocio y el modelo de datos se capturan en objetos, usando tecnologías como CORBA, RMI, DCOM.

**Ventajas:**
- Menor acoplamiento entre cliente y servidor.
- Código más reusable.

**Desventajas:**
- Mayor complejidad en el servidor intermedio.

**Explicación:**
Aquí se usan interfaces para definir cómo interactúan los componentes, facilitando la reutilización y el mantenimiento.

---

### 1.5. Tres Niveles con Servidor Web

El navegador maneja la presentación y se comunica con el servidor vía HTTP. El servidor genera contenido dinámico usando tecnologías como CGI, Servlet/JSP, ASP.

**Ventajas:**
- El cliente es universal (navegador web).
- No requiere instalación ni administración en el cliente.
- Soporta múltiples dispositivos.

**Desventajas:**
- Complejidad en el servidor intermedio.

**Explicación:**
Este es el modelo más usado hoy en día. Permite que cualquier dispositivo con navegador acceda a la aplicación.

---

### Diferencia entre RPC, objetos remotos y servidor web

RPC y objetos remotos son modelos de comunicación directa entre aplicaciones, con diferentes niveles de abstracción y acoplamiento, teniendo RPC un mayor acoplamiento entre el cliente y el servidor intermedio.
El servidor web usa el navegador como cliente y HTTP como protocolo, facilitando el acceso desde cualquier dispositivo.

---

## 2. Tendencias en Arquitectura Web

- Migración de arquitecturas monolíticas a multinivel.
- Uso de aplicaciones basadas en objetos.
- Preferencia por clientes basados en HTML.

**Explicación:**
Las aplicaciones modernas buscan ser **flexibles**, **escalables** y **fáciles de mantener**. Separar responsabilidades es clave.

---

## 3. Comparaciones Clave

### Simple Nivel vs Multinivel

- **Simple Nivel:** Todo junto, difícil de mantener.
- **Multinivel:** Separación de responsabilidades, más flexible y fácil de modificar.

### Monolíticos vs Basado-Objetos

- **Monolíticos:** Un solo archivo binario, requiere recompilar y redesplegar ante cualquier cambio.
- **Basado-Objetos:** Permite reutilización, mejor diseño y mantenimiento.

**Explicación:**
La modularidad y la reutilización son fundamentales para el desarrollo profesional.

---

## 4. Complejidad del Servidor de Nivel-Medio

Las aplicaciones empresariales requieren servicios como concurrencia, transacciones, seguridad y administración de recursos. Para resolver esto, se usan contenedores que proveen estos servicios.

### Soluciones

- **Propietarias:** Contratos bien definidos pero dependientes de un proveedor (ejemplo: .NET).
- **Estándar-Free:** Uso de estándares abiertos como JEE, que permiten portabilidad y flexibilidad.

**Explicación:**
Elegir tecnologías abiertas facilita la integración y el crecimiento profesional.

---

## 5. ¿Cómo funciona la Web?

La web es un sistema de documentos accesibles por internet mediante navegadores. El usuario ingresa una URL, el navegador consulta el DNS para obtener la IP, envía una petición HTTP y recibe el contenido (HTML, imágenes, videos, etc.).

**Explicación:**
Cada vez que navegas por internet, tu navegador realiza estos pasos para mostrarte una página.

---

## 6. Evolución de la Web

- **Web 1.0:** Sitios estáticos, no interactivos.
- **Web 2.0:** Enfoque en el usuario, redes sociales, wikis, blogs, mashups.
- **Web 3.0:** Añade significado, interconexión de datos, evolución del conocimiento.
- **Web 4.0:** Comunicación entre inteligencias humanas y artificiales para la toma de decisiones.

**Explicación:**
La web ha pasado de ser solo información a ser una plataforma colaborativa y ahora inteligente.

---

## 7. Diccionario de Términos Clave

**API**
Inglés: Application Programming Interface
Castellano: Interfaz de Programación de Aplicaciones
¿Para qué sirve?: Permite que diferentes programas se comuniquen entre sí, definiendo cómo interactuar con un sistema o servicio.

**AWT**
Inglés: Abstract Window Toolkit
Castellano: Herramientas de Ventanas Abstractas
¿Para qué sirve?: Biblioteca de Java para crear interfaces gráficas de usuario (ventanas, botones, etc.).

**CDI**
Inglés: Context and Dependency Injection
Castellano: Inyección de Contexto y Dependencias
¿Para qué sirve?: Permite gestionar dependencias y el ciclo de vida de los objetos en aplicaciones Java.

**CGI**
Inglés: Common Gateway Interface
Castellano: Interfaz Común de Entrada
¿Para qué sirve?: Protocolo para ejecutar programas externos desde un servidor web y generar contenido dinámico.

**DNS**
Inglés: Domain Name System
Castellano: Sistema de Nombres de Dominio
¿Para qué sirve?: Traduce nombres de dominio (como www.ejemplo.com) a direcciones IP.

**EJB**
Inglés: Enterprise Java Beans
Castellano: Componentes Empresariales Java
¿Para qué sirve?: Componentes reutilizables para lógica de negocio en aplicaciones Java empresariales.

**GUI**
Inglés: Graphical User Interface
Castellano: Interfaz Gráfica de Usuario
¿Para qué sirve?: Permite la interacción visual entre el usuario y el software mediante ventanas, botones, etc.

**HTTP**
Inglés: HyperText Transfer Protocol
Castellano: Protocolo de Transferencia de Hipertexto
¿Para qué sirve?: Protocolo que permite la comunicación entre navegadores y servidores web.

**IP**
Inglés: Internet Protocol
Castellano: Protocolo de Internet
¿Para qué sirve?: Identifica y localiza dispositivos en una red para que puedan comunicarse.

**JASPIC**
Inglés: Java Authentication Service Provider Interface for Container
Castellano: Interfaz de Proveedor de Servicio de Autenticación para Contenedores Java
¿Para qué sirve?: Permite implementar mecanismos de autenticación en aplicaciones Java empresariales.

**JCP**
Inglés: Java Community Process
Castellano: Proceso de Comunidad Java
¿Para qué sirve?: Organización que define y evoluciona los estándares de la plataforma Java.

**JNDI**
Inglés: Java Naming and Directory Interface
Castellano: Interfaz de Nombres y Directorios en Java
¿Para qué sirve?: Permite buscar y acceder a recursos (como bases de datos) en aplicaciones Java.

**JSF**
Inglés: JavaServer Faces
Castellano: Caras de Servidor Java
¿Para qué sirve?: Framework para construir interfaces de usuario web en Java.

**JSP**
Inglés: JavaServer Pages
Castellano: Páginas de Servidor Java
¿Para qué sirve?: Tecnología para crear páginas web dinámicas usando Java en el servidor.

**JSR**
Inglés: Java Specification Requests
Castellano: Solicitudes de Especificación Java
¿Para qué sirve?: Documentos que proponen y definen nuevas características para Java.

**Mashup**
Inglés: Mashup
Castellano: Mezcla o combinación
¿Para qué sirve?: Aplicación web que integra datos y servicios de diferentes fuentes para crear algo nuevo.

**POJO**
Inglés: Plain Old Java Object
Castellano: Objeto Java Simple
¿Para qué sirve?: Objeto Java que no depende de ninguna tecnología o framework especial.

**RPC**
Inglés: Remote Procedure Call
Castellano: Llamada a Procedimiento Remoto
¿Para qué sirve?: Permite que un programa ejecute funciones en otro servidor como si fueran locales.

**URL**
Inglés: Uniform Resource Locator
Castellano: Localizador Uniforme de Recursos
¿Para qué sirve?: Dirección que identifica recursos en la web, como páginas o archivos.

**Explicación:**
Estos términos son fundamentales para entender la jerga técnica en el desarrollo web y backend.

---

## 8. Conclusión

La evolución de la web y sus arquitecturas nos muestra la importancia de separar responsabilidades y usar estándares abiertos. Esto permite crear aplicaciones escalables, mantenibles y flexibles, preparándonos para los desafíos del desarrollo backend moderno.

