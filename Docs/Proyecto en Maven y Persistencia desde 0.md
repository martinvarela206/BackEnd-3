# Proyecto en Maven y Persistencia desde 0

## Crear la BBDD

```sql
-- Crear la base de datos
DROP DATABASE IF EXISTS universidad;
CREATE DATABASE IF NOT EXISTS universidad;
USE universidad;

-- Tabla: facultad
CREATE TABLE facultad (
    idfacultad INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    PRIMARY KEY (idfacultad)
) ENGINE=InnoDB;

-- Tabla: carrera
CREATE TABLE carrera (
    idcarrera INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    fk_idfacultad INT NOT NULL,
    PRIMARY KEY (idcarrera),
    INDEX fk_carrera_facultad_idx (fk_idfacultad ASC),
    CONSTRAINT fk_carrera_facultad
        FOREIGN KEY (fk_idfacultad)
        REFERENCES facultad (idfacultad)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla: alumno
CREATE TABLE alumno (
    idalumno INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    registro VARCHAR(6) NOT NULL,
    fk_idcarrera INT NOT NULL,
    PRIMARY KEY (idalumno),
    INDEX fk_alumno_carrera1_idx (fk_idcarrera ASC),
    CONSTRAINT fk_alumno_carrera1
        FOREIGN KEY (fk_idcarrera)
        REFERENCES carrera (idcarrera)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla: materia
CREATE TABLE materia (
    idmateria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(25) NOT NULL,
    PRIMARY KEY (idmateria)
) ENGINE=InnoDB;

-- Tabla: examen
CREATE TABLE examen (
    fk_idmateria INT NOT NULL,
    fk_idalumno INT NOT NULL,
    fecha TIMESTAMP(6) NOT NULL,
    nota INT,
    PRIMARY KEY (fk_idmateria, fk_idalumno),
    INDEX fk_materia_has_alumno_alumno1_idx (fk_idalumno ASC),
    INDEX fk_materia_has_alumno_materia1_idx (fk_idmateria ASC),
    CONSTRAINT fk_materia_has_alumno_materia1
        FOREIGN KEY (fk_idmateria)
        REFERENCES materia (idmateria)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_materia_has_alumno_alumno1
        FOREIGN KEY (fk_idalumno)
        REFERENCES alumno (idalumno)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;
```

```sql
delete from materia;
insert into materia (idmateria,nombre) values (1,"Matematica");
insert into materia (idmateria,nombre) values (2,"Programacion");
insert into materia (idmateria,nombre) values (3,"Frontend");
insert into materia (idmateria,nombre) values (4,"Backend");
select * from materia;
```

```sql
delete from carrera;
insert into carrera (idcarrera, nombre)

## Creando el proyecto web

![alt text](image.png)

![alt text](image-1.png)

![alt text](image-2.png)

1. Crear un proyecto nuevo de Java with Maven > Web Application.
2. Name: Colocar nombre del proyecto.
3. Group Id y Version por ahora no son muy importantes.
4. Luego de elegir GlassFish y Jakarta EE 10, darle a `finish`.

## Añadir la dependencia para MySQL

1. Ir a: <https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.33>
2. Copiar el codigo para Maven:

```xml
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

> [!INFO]
> Al crear el proyecto con Maven, ya se añadio una dependencia por defecto que es la que controla la API de Jakarta EE, que permite el uso de servlets, JSP, JPA, etc. No hay que borrarla.

3. Abrir el `Project File/pom.xml` y añadir en `<dependencies />`.
4. Correr el proyecto (con esto, la primera vez, se va a descargar la dependencia).

## Configurar Glassfish para MariaDB

1. Descargar `mariadb-java-client-3.5.6.jar`
2. Copiar el Jar a la carpeta de extensiones de GlassFish: `/home/martin/glassfish/glassfish/domains/domain1/lib/`
3. Reiniciar GlassFish: Desde Netbeans > Services > Servers > Botón derecho sobre Glassfish > Restart.
4. Abrir la consola de Glassfish desde Netbeans > Services > Servers > Botón derecho sobre Glassfish > View Admin Console.

### Crear el Pool (JDBC Connection Pool)

1. Ir a Resources > JDBC > JDBC Connection Pools, y crear uno nuevo.
2. Completar los siguientes campos:
  - Pool Name: `UniversidadPool`
  - Resource Type: `javax.sql.DataSource`
  - Database Vendor: `MySQL`
  - Darle a Next.
  - Datasource Classname: `org.mariadb.jdbc.MariaDbDataSource`
3. Añadir las siguientes propiedades:
  - url: `jdbc:mariadb://localhost:3306/universidad`
  - user: `root`
  - password: `admin1234` (el pass de MariaDB)

## Crear el JDNI (JDBC Resource)

Luego de crear el Pool, hay que crear el JDNI:

1. Ir a Resources > JDBC > JDBC Resources > New:
- Darle el nombre al JDNI, por ejemplo `jdbc/universidad`.
- Seleccionar el Pool creado antes.

## Testear la conexion a la BBDD

### 1. Crear el Datasource para Netbeans

Esta configuración es necesaria para que Glassfish defina el JDNI de la base de datos.

1. Descargar el Jar del conector: <https://mariadb.com/downloads/connectors/>: Hay que seleccionar Producto (Java).
![alt text](image-4.png)
2. Ir a Services > click derecho en DataBases > New Connection > Seleccionar MariaDB en Driver y Add para añadir el Jar.
3. Configurar la contraseña para el root de MariaDB (y cualquier otra configuración necesaria) y principalmente la BBDD a usar.

![alt text](image-5.png)

Luego se puede probar que esta conectando a la BBDD correcta ejecutando `show tables;` y debería mostrar las tablas de la BBDD.

> [!INFO]
> Desde consola se puede ejecutar: `sudo mariadb -u root -p` (pide el pass sudo y el pass de la DDBB), luego `USE universidad;` y finalmente `SHOW TABLES;` para ver las tablas.
> Para salir se puede poner `EXIT;`

### 2. Crear la entidad Java para tabla Facultad

- Boton derecho sobre el proyecto > New > Entity Classes from Database

![alt text](image-6.png)

- Hay que seleccionar Local Data Source y luego `jdbc:mariadb://localhost:3306/universidad`.
- Cuando carga las tablas, darle a `Add all` > Next > Next > tildar `Fully ...` y `Attributes ...` y darle a Finish.

Esto va a generar las siguientes entidades:

Facultad, Carrera, Alumnos, Examen, Materia

![alt text](image-7.png)

Ademas de las entidades para las tablas, se ha creado una entidad mas, llamada **ExamenPK**, esto se debe a que la tabla **Examen** contiene **dos PK**, uno hacia **Alumno** y otro hacia **Materia**.

## Configurar persistence.xml

Esto se hace siempre a mano.

En `src/main/resources/META-INF/persistence.xml`:

```xml
<persistence version="3.0"
             xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="UniversidadPU" transaction-type="JTA">
    <jta-data-source>jdbc/universidad</jta-data-source>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties/>
  </persistence-unit>
</persistence>
```

## Probar listar las Materias

Hay que crear el jsp que las muestra, pero tambien el controlador (servlet) que obtiene los datos de la entidad (modelo).

> [!WARNING]
> Hay que editar el package de las entidades, de acuerdo al package generado.

En Source Package > New Servlet:

```java
// servlet
package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.*;
import com.mypackage.Materia; // Ajusta el paquete según tu proyecto
import java.io.IOException;
import java.util.List;

@WebServlet("/materias")
public class MateriaServlet extends HttpServlet {
    // Hay que editar src/main/resource/META-INF/persistence.xml para darle el nombre a la Unidad de Persistencia
    @PersistenceContext(unitName = "UniversidadPU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> materias = em.createQuery("SELECT m FROM Materia m", Materia.class).getResultList();
        request.setAttribute("materias", materias);
        request.getRequestDispatcher("materias.jsp").forward(request, response);
    }
}
```

```java
//jsp
<!-- filepath: src/main/webapp/materias.jsp -->
<%@ page import="java.util.List, com.mypackage.Materia" %>
<% List<Materia> materias = (List<Materia>) request.getAttribute("materias"); %>
<html>
<head><title>Materias</title></head>
<body>
<h2>Materias</h2>
<table border="1">
<tr><th>ID</th><th>Nombre</th></tr>
<% if (materias != null) {
     for (Materia m : materias) { %>
  <tr>
    <td><%= m.getIdmateria() %></td>
    <td><%= m.getNombre() %></td>
  </tr>
<%   }
   } %>
</table>
</body>
</html>
```

## JPQL

En el servlet antes creado se está utilizando JPQL (Java Persistence Query Language) para obtener todas las materias de la base de datos.

JPQL es un lenguaje de consulta similar a SQL, pero está diseñado para trabajar con entidades en lugar de tablas. Permite realizar consultas sobre los objetos de la aplicación en lugar de sobre las tablas de la base de datos directamente.











```java
package model;

import jakarta.persistence.*;

@Entity
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfacultad;
    private String nombre;

    // Getters y setters
    public Integer getIdfacultad() { return idfacultad; }
    public void setIdfacultad(Integer id) { this.idfacultad = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
```



### 3. Crear el servlet para consultar las facultades

```java
package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.*;
import model.Facultad;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class FacultadServlet extends HttpServlet {
    @PersistenceContext(unitName = "UniversidadPU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Facultad> facultades = em.createQuery("SELECT f FROM Facultad f", Facultad.class).getResultList();
        request.setAttribute("facultades", facultades);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
```

### 4. Editar el index.jsp

```java
<%@ page import="java.util.List, model.Facultad" %>
<% List<Facultad> facultades = (List<Facultad>) request.getAttribute("facultades"); %>
<html>
<head><title>Facultades</title></head>
<body>
<h2>Facultades</h2>
<table border="1">
<tr><th>ID</th><th>Nombre</th></tr>
<% if (facultades != null) {
     for (Facultad f : facultades) { %>
  <tr>
    <td><%= f.getIdfacultad() %></td>
    <td><%= f.getNombre() %></td>
  </tr>
<%   }
   } %>
</table>
</body>
</html>
```
