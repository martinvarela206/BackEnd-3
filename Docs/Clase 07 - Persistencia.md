# Persistencia en Java

## Introducción

Cuando se habla de OO, se habla de encapsula, comportamiento, estado, Herencia, polimorfismo, colecciones, clases abstractas y clases concretas.

Cuando se habla de persistencia, se habla generalmente de bbdd relacionales, tablas, claves primarias, indices, relaciones entre tablas, formas normales, y tambien otras bbdd no relacionales. Hay que identificar entidades, armar el modelo entidad relaciones. El diseño es fundamental, es la clave del exito.

La idea en Java es combinar la persistencia con los objetos, para trabajar orientado a objetos. Entre ambos, hay una interfaz que se encarga de mapear el objeto a tabla. La idea no es utilizar SQL puro, que la interfaz se encargue de hacer los "SELECT".

En java el componente mas primitivo, mas básico es el JDBC (el driver), luego la herramienta que realiza el mapeo, es una herramienta como Hibernate, TopLink, JDO. Y finalmente hay una libreria que integra la herramienta de mapeo entre el modelo relacional con el objeto, como JPA.

// TODO: Corregir lo anterior.

Java debe despegarse del hardware y por lo tanto del motor de BBDD, usar SQL puro baja el nivel de abstraccion y deja pegada la app al motor de la BBDD.

## Especificaciones de JPA

## Conceptos

- Que es una entidad y un objeto.
- Que estrategias hay para realizar el mapeo.
- Que significa la unidad de persistencia.

Objeto: Son instancias que viven en memoria, durante la ejecucion del programa.
Entidad: Son instancias que ademas se persisten en BBDD, por lo que su ciclo de vida va mas alla de la ejecucion del programa.
Mapeo Relacional-Objetos: Esta definido en `jakarta.persistence` y utiliza directivas al sistema en la propia clase como `@Override` o bien usando descriptores XML (archivos externos), colocar todas las directivas.
Unidad de persistencia: Todo lo necesario para conectarse a la BBDD esta en el archivo `persistence.xml`, todo eso es la informacion necesaria para conectarse y generar la BBDD, es la unidad de persistencia. El proveedor será eclipseLink, es quien se encarga de generar dicho archivo.

## Recursos

- Netbeans 25
- JDK 11 (las versiones de JDK pueden convivir sin problemas): 
    - Descargar: https://www.oracle.com/latam/java/technologies/downloads/#java11
    - Instalar: `sudo dpkg -i ~/Descargas/jdk-11.0.28_linux-x64_bin.deb`
- Servidor: GlassFish Server 7.0.15 (o 25) (requiere JDK 11), ya incluye Derby
    - Descargar, mover el archivo zip a la carpeta del usuario: `mv ~/Descargas/glassfish-7.0.15.zip ~/glassfish.zip`
    - Descomprimir:  `unzip ~/glassfish.zip -d ~/` (descomprime en glassfish7)

## Pasos del proceso

1. Crear la BBDD (tablas, indices, relaciones, etc)
2. Crear el proyecto web (elegir GlassFish como servidor).
3. Generar la clase entidad.
4. Crear unidad de persistencia.
5. Generar la clase java que manejara la entidad.

## Clase menejador

Es un servlet. En el ejemplo se ve un servlet Manejador para 3 peticiones diferentes, por medio de switch/cases.

Primero se crea el objeto con los datos enviados por formulario, luego se hace persistente y se vuelve al index.

## Nueva conexion
Añadir GlassFish a Netbeans: Tools > Servers > Add Server > GlassFish Server > Colocar la carpeta donde esta GlassFish para que detecte la version > Next > Finish
(???) En la solapa Services > Database hacer click derecho y New connection > Java DB (Embedded) > ???
Borrar la BD sample.
Crea la BD: Solapa Services > Database > Java DB > Boton derecho > Create Database: DBAlumno/user:app/pass:app
En el jdbc darle boton derecho Connect.
jdbc > APP > Tables > Boton derecho y crear tablas

## Crear el proyecto web
- Crearlo con Ant
- Reemplazar por el ejercicio de la profe
    - Instalar unrar: `sudo apt update` y `sudo apt install unrar -y`
    - Descomprimir: `unrar x ~/Descargas/GestionAlumno.rar`

## Generar la Entidad

La primera vez:
Project > New > Other > Persistence > Entity Classes from Database > Server Data Source > New Data Source > Add All > Next > Seleccionar parquete > Next > Marcar Fully y Attributes > Finish.

Añadir JDK 11: Tools > Java Plataforms > JDK 11.

Modificar en Configuration Files > Persistence.xml > 3.0

Hay que crear el Listar.jsp

Y probar.

## Ahora hacerlo para Maven

1. Crear el proyecto con maven
2. Copiar servlets y jsp.
3. Añadir Entity Clases From Database
4. Editar el `src/main/resources/META-INF/persistence.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="3.0"
             xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                                 https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="AlumnosPU" transaction-type="JTA">
    <jta-data-source>java:app/DBAlumno</jta-data-source>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties/>
  </persistence-unit>
</persistence>
```

Esto debería funcionar.

<https://youtu.be/pd5kVcJTf10>



