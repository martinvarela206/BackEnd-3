# JAVA EE (Componentes Web)

## Introducción

“Hoy en día los desarrolladores
reconocen la necesidad de aplicaciones
portables, transaccionales y
distribuidas que aprovechen la
velocidad, seguridad y confiabilidad
de las tecnologías del lado del
servidor.”
Las aplicaciones enterprise deben ser
diseñadas, construidas y producidas
con: -dinero +velocidad -recursos

## Modelo AppEE

La plataforma Java EE utiliza un
“modelo de aplicación distribuida
de varios niveles”.
 La lógica de una aplicación se divide en
componentes de acuerdo a su función.
 Estos componentes, que conforman una
aplicación Java EE, se instalan en
máquinas diferentes.
https://docs.oracle.com/javaee/7/firstcup/java-ee001.htm

Java EE plataform is designed to help developers create large-scale, multi-tiered, scalable, reliable and secure network applications.

Una aplicación web puede ser:
1. Orientada-Presentación: Genera
páginas web interactivas, basada en
requerimientos / respuestas.
2. Orientada-Servicios: La aplicación
web implementa el endpoint del
Servicio Web.
- SOAP XML WSDL UDDI -
- REST FULL -

Las especificaciones EE define los
siguientes componentes:
 Aplicaciones cliente y applet, corren sobre el
cliente.
 Servlet, JSP y JSF son componentes web que
corren sobre el servidor.
 JavaBeans son componentes de negocio que
corren sobre el servidor.

Maquina Cliente (Nivel Cliente): Aplicación EE (cliente o web)
Servidor Java EE (Nivel Web): JSF y JSP
Servidor Java EE (Nivel Negocio): JavaBeans
Servidor BBDD (Nivel EIS): Bases de datos

## Clientes web

 Páginas Dinámicas que contiene código
en distintos lenguajes (HTML, XML),
generado por un componente web.
 Browser web, que interpreta la página
recibida del servidor.

## Aplicacion cliente

 Provee la manera para que los usuarios
manejen las tareas a través de una
interfaz gráfica (también línea de
comandos).
 Pueden acceder al Nivel de negocios
siempre y cuando la aplicación lo
garantice.

## Diferencia entre clientes web y aplicaciones cliente

La aplicacion cliente se conecta directamente al nivel negocio, mientras que el cliente web pasa primero por el nivel web y este se conecta al nivel negocio.

## Componentes web

 Servlet: Son clases del lenguaje java,
que procesan peticiones
dinámicamente y construyen las
respuesta.
 Java Server Page: Es un documento
de texto que se ejecuta como un
servlet, permite contenido estático.
 Java Server Face: Es la interface de
usuario construida sobre servlet y
JSP.

## Componentes Negocio (Javabeans)

 Es el código (lógica) que resuelve y
satisface las necesidades de un dominio
particular de la empresa.
 Los Sistemas Información de la Empresa
incluyen, procesamiento de
transacciones, sistemas de base de
datos, otros sistemas de información
heredados.

## Componentes y contenedores

 Los componentes web (unidades de
software) están en la forma de Servlet o
JSP (junto con JavaBean)
 Los componentes corren en un
contenedor Web
–Tomcat, GlassFish, Resin
–Todos los servidores compatibles con
app J2EE proveen contenedores web
 Los contenedores constituyen la
interface entre los componentes y las
funcionalidades específicas de bajo
nivel.

## Netbeans

En el desarrollo:

1.Escribir y compilar el código (Servlet orJSP)
2.Crear un recurso estático (Por ej.: una
imagen)
3.Crear el descriptor de despliegue (web.xml)
4.Construir la aplicación web (archivo *.war o
un directorio listo para despliegue)
5.Desplegar la aplicación en un contenedor
web
     Los clientes web están listos para acceder via URL

### 1. Escribir y compilar

Al momento de escribir y compilar:
 Crear una estructura de carpetas y subcarpetas,
para conservar el código fuente separado del
código compilado.
 Escribir el código servlet y/o la página JSP
 Crear build.xml
Es un documento de texto en formato xml que incluye
tareas especificas a realizar antes o después de iniciar
el proyecto, la compilación, etc..
 El entorno NetBeans maneja estas tareas.

### 2. Crear recursos estaticos

 Páginas HTML.
 Archivos imágenes que son usados por los documentos
JSP y HTML
En la arquitectura EE son llamados
recursos web.

### 3. Descriptor de despligue

 El descriptor web.xml contiene
instrucciones de despliegue en tiempo
de ejecución para el contenedor web.
– URL que el cliente usa para acceder al
componente web.
…sun-web.xml es el descriptor para
Glassfish…
 Si la aplicación solo tiene documentos
html y archivos imágenes no necesita
el descriptor.

### 4. Construir la aplicacion

El proceso de generar o build la aplicación consta de:
 Crear la carpeta build y sus subcarpetas.
 Compilar los archivos .java y guardarlos en la carpeta
build/WEB-INF/classes
 Crear la carpeta dist con el archivo .war
     Es un archivo compacto listo para
    ser desplegado sobre cualquier
    contenedor web.
     Componentes Web (servlets or
    JSP's).
     Clases del lado-Servidor
     Contenido estático de la
    presentación Web
     Clases del lado-cliente
     Refleja su contenido en la carpeta
    build

### 5. Desplegar la aplicacion

La aplicación puede ser desplegada de dos
maneras diferentes:
 archivo *.war
 Carpeta descomprimida con el mismo
formato del archivo *.war
Usar el archivo *.war cuando sea
desplegado desde una máquina remota