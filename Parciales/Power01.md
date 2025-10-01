# Control 1

## La web como plataforma

### Simple nivel

Terminales bobas conectadas directamente al 
 mainframe
  Modelo Centralizado.
  Aplicación monolítica que concentra la 
 presentación, la lógica del negocio y el
 acceso a los datos. 

 Pro: 
 No se requiere gestiones del lado del cliente
 La consistencia de los datos es fácil de lograr
Contras:
Funcionalidad (presentación, modelo de datos, lógica del negocio) entrelazada, difícil para modificar, mantener y reusar. 

### Dos niveles

Clientes Gruesos  conectados a la Base de Datos:
  Transmite consultas SQL, retorna filas de datos
  Presentación, lógica del negocio y el modelo de 
 datos se procesan en la aplicación cliente.

 Pro: 
Producto BD independiente.
Contras:
Presentación, modelo de datos, lógica del negocio están entrelazadas (lado cliente), difícil para modificar y mantener
Modelo de Data es “estrechamente acoplado” a cada cliente: Si cambia el esquema de DB, todos los clientes quiebran. 
Las actualizaciones deben ser desplegadas en todos los clientes, convirtiendo en una pesadilla el mantenimientos del sistema.
Conexión DB por cada cliente, por lo tanto difícil de escalar.
Transferir datos a los clientes causa un elevado trafico sobre la red. 


### Tres niveles (RPC)

Cliente Delgado: negocio y modelo de datos separados de la presentación
 La lógica del negocio y la lógica de acceso a datos residen en el servidor de nivel medio, mientras que el cliente maneja la presentación.

  Servidor Nivel Intermedio que ahora se necesita para manejar el sistema 
    de servicios:
 Control de concurrencia, thread (hilos), transacciones, seguridad,      persistencia, multiplexación, rendimiento, etc. 

Pro:
La lógica del negocio puede cambiar mas que en modelo de Nivel 2.
En el servidor reside mas lógica del negocio.
Contras:
Se introduce mas complejidad en el servidor.
Cliente y el servidor están mas acoplados.
El código no es realmente reusable

### Tres niveles (Objetos remotos)

Lógica del negocio y modelo de datos capturada en objetos:
Se describen de manera abstracta (leng. de interface)
Modelos de Objetos usados: CORBA, RMI, DCOM
Lenguaje de Interface en CORBA es IDL.
Lenguaje de Interface en RMI es interface Java.

Pro:
Mas débilmente acoplados que el modelo RPC.
Código reusable.
Contras:
Complejidad del Nivel-Medio.
Es necesario que sea abordada.

### Tres niveles (Servidor web)

Browser maneja la presentación.
Browser trata con el Servidor vía HTTP.
Lógica del negocio y modelo de datos manejado por tecnologías que “generan contenidos dinámicamente”. (CGI, Servlet/JSP, ASP).

Pro:
Tipo de cliente ubicuo.
Cero administración cliente.
Soporta distintos dispositivos clientes:
JME: celulares, palm.
Contras:
Complejidad del Nivel-Medio.
Es necesario que sea abordada.

### Tendencias

Pasar de arq. de un Nivel o dos Niveles a la Arquitectura MultiNivel.
Pasar de modelo monolítico a un  modelo de aplicaciones basado en objetos.
Pasar de un cliente basado-aplicación a un cliente basado-HTML. 


### Simple nivel vs Multinivel

Simple Nivel

 No hay separación
  entre presentación,
  lógica del negocio, 
  Base de datos.

 Difícil de mantener

Multi Nivel

 Separación entre
  presentación,
  lógica del negocio, 
  Base de datos.

 Mas fexibilidad
  para cambiar. Ej: 
  puede cambiar la
  presentación sin
  afectar otros niveles

### Monolíticos vs Basado-Objetos

Monolíticos: Un archivo binario.

  Recompliar, 
   redesplegar cada
   vez que cambia. 

Basado-Objetos: Reusable.
 Permite mejor diseño
 Fácil mantener.
 Implementación
  puede estar separada
  de la interface.
 Solo la interface es

### Cuestiones pendientes

Permanece la complejidad del Servidor de Nivel-Medio
Las aplicaciones enterprise deben duplicar servicios:
Control concurrencia, Transacciones
Seguridad
Administración de recursos
¿Como resolver este problema?
Comúnmente compartir contenedores que maneja el sistema por encima de los servicios.
Propietarios vs estándar-free

### Solución Propietaria

Usar modelo "componentes y contenedores" 
Componentes capturan la lógica del negocio
Contenedores proveen servicios
El contrato entre componentes y contenedor esta bien definido pero es propietaria.
Ej.: .NET – Ahora con licencia Free pero tener en cuenta el Sistema Operativo.

### Solución Free

Usa "componentes y contenedores" en el que los contenedores proveen los servicios del sistema bien definidos y como estándares en la industria.
JEE es estándar y además provee portabilidad de código debido a que esta basado en la tecnología Java.

### La web como plataforma

www - world wide web
   Sistema de documentos basados en hipertexto    accesibles a través de internet. Por medio de un software, navegador, que permite visualizar un sitio web.
¿Cómo?
El usuario ingresa una dirección (URL) en el navegador.
El navegador inicia una serie de comunicaciones para  obtener los datos de la página.
Convierte el nombre del servidor en una dirección IP, utilizando la base de datos DNS.
Se envía una petición HTTP al servidor, para acceder al recurso:
Se solicita el texto HTML.
Lo analiza el navegador y realiza peticiones adicionales: gráficos , videos, etc. 

### Evolución de la Web

Web 1.0: Los sitios son estáticos: Contiene información que puede ser muy útil pero no cambia, por lo tanto el usuario no tiene motivos para volver.
No son interactivos: Los usuarios no pueden contribuir ni aportar al sitio.
Las aplicaciones son propietarias: La filosofía de la web 1.0 es que las empresas desarrollan aplicaciones que los usuarios pueden descargar pero no pueden ver como trabajan.

Web 2.0 (2004): Mas que una tecnología, es la actitud para enfrentar el desarrollo de las aplicaciones.
Las aplicaciones están enfocadas en el usuario final con tecnologías como:
Redes sociales
e-learning
Wikis: la creación de contenido de forma colaborativa. sistema de creación, intercambio y revisión de información en la web, de forma fácil y automática
Blogs: se enfocan en una temática en particular y permite la entrada de comentarios
Mashup: Aplicación que usa y combina datos, presentaciones y funcionalidad procedentes de otras fuentes para crear nuevos servicios.

Web 3.0: Pretende:
 Añadir significado a la web. 
Ser capaz de interpretar e interconectar un gran número de datos, permitiendo un avance en el campo del conocimiento.
Asistir a la evolución del conocimiento humano en su totalidad.

Andrés Richero, presenta lo siguiente: 
Web 1.0 – Personas conectándose a la Web
Web 2.0 – Personas conectándose a personas.
Web 3.0 – Aplicaciones web conectándose a aplicaciones web, a fin de enriquecer la experiencia de las personas.

Web 4.0: Objetivo primordial:
  Comunicar “las inteligencias” de las  personas y las cosas para generar la toma de decisiones. 

Existen agentes en la Web que conocen, aprenden y razonan como lo hacemos las personas.

### Diccionario

API  - Application Programming Interface.
AWT - Abstract Windows Toolkit.
CDI  - Context and Dependency Injection (Nueva tecnología).
CGI - Common Gateway Interface.
DNS - Domain Name System (Sistema de nombres de dominio)
EIS  - Enterprise Information System.
EJB - Enterprise Java Beans.
GUI - Graphical User Interface.
HTTP - HiperText Transfer Protocol.
IP  - Internet Protocol
JASPIC - Java Authentication Service Provider Interface for Container
     (Nueva Tec.).
JCP - Java Community Process. Responsables de todas las tec.
JNDI - Java Naming and Directory Interface.
JSF - Java Server Face.
JSP - Java Server Page.
JSR - Java Specification Requests. Define varias de las tecnologías JEE.
MASHUP - Es una pagina web o una aplicación que integra elementos complementarios de una o mas fuentes. Frecuentemente son creados usando Ajax. Ej.:  
Panoramio : un mashup de Google Maps y fotos geoposicionadas de ubicación
Hiking Outpost : un mashup de Amazon y recursos de información online para excursiones.
Flash Earth : un mashup de Google Maps y Virtual Earth de Microsoft.
HousingMaps : un mashup de Google Maps y anuncios de alquiler Craigslist que muestra la información geográfica de las propiedades de alquiler.
POJO  - Plain Old Java Object
RPC  - Remote Procedure Call
URL  - Uniform Resource Locator – Localizador Uniforme de Recursos

