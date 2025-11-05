# Proyecto en Maven y Persistencia desde 0

## Requerimientos previos

- Netbeans 25
- JDK 11 (las versiones de JDK pueden convivir sin problemas): 
    - Descargar: <https://www.oracle.com/latam/java/technologies/downloads/#java11>
    - Instalar: `sudo dpkg -i ~/Descargas/jdk-11.0.28_linux-x64_bin.deb`
    - Configurar la Platform en Netbeans: `Tools > Java Platforms > Add Platform > Seleccionar JDK 11`
- Servidor: GlassFish Server 7.0.15 (o 25) (requiere JDK 11, quizas ya esta instalado el JDK 24, pero no sirve), ya incluye Derby
    - Descargar: <https://glassfish.org/download_gf7.html> el que dice `Platform`
    - Mover el archivo zip a la carpeta del usuario: `mv ~/Descargas/glassfish-7.0.15.zip ~/glassfish.zip`
    - Descomprimir (en una carpeta de acceso común, en linux seria la carpeta del usuario):  `unzip ~/glassfish.zip -d ~/` (descomprime en glassfish7)
- BBDD: MariaDB
- Luego configurar mariadb (root password y privilegios):
```sh
ALTER USER 'root'@'localhost' IDENTIFIED BY 'admin1234';
FLUSH PRIVILEGES;
EXIT;
```

> [!INFO]
> En Windows, NetBeans da error si no existe un password para el root. Por lo tanto hay que configurar XAMPP y phpMyAdmin para usar el usurio root con password.

1. Ingresar a phpMyAdmin > Cuentas de Usuario: Ponerle contraseña a todos los usuarios root. En el momento en que root(localhost) tenga password, phpMyAdmin fallara.
2. Ubicar el archivo: `C:\xampp\phpMyAdmin\config.inc.php`
3. Editar la línea: `$cfg['Servers'][$i]['password'] = '';` y poner el password que se le asigno a root, por ejemplo: `$cfg['Servers'][$i]['password'] = 'admin1234';`

> [!WARNING]
> No se puede hacer por el `my.ini`, si o si hay que editar el `.php`.

## Crear la BBDD en MariaDB

En **Windows** simplemente usar el administrador de XAMPP para crear la BBDD.

En **Linux** hay que hacerlo por terminal:
1. Entrar a MariaDB: `sudo mariadb -u root -p` (pone el pass de sudo y luego el pass de root de MariaDB)
2. Crear la BBDD: `CREATE DATABASE IF NOT EXISTS universidad;`
3. Salir: `EXIT;`

## Conectar Netbeans a MariaDB

### 1. Crear el Datasource para Netbeans

Esta configuración es necesaria para que luego Glassfish defina el JDNI de la base de datos.

1. Descargar el Jar del conector: <https://mariadb.com/downloads/connectors/>: Hay que seleccionar Producto (Java).
![alt text](image-4.png)
2. Ir a Services > click derecho en DataBases > New Connection > Seleccionar MariaDB en Driver y Add para añadir el Jar.
![alt text](image-11.png)
3. Configurar la contraseña para el root de MariaDB (y cualquier otra configuración necesaria) y **principalmente** la BBDD a usar.
![alt text](image-5.png)
4. No seleccionar ningun schema.
5. El nombre debería quedar como: `jdbc:mariadb://localhost:3306/alumnosapp [root on Default schema]`
6. Ir a Databases > jdbc > click derecho > Execute comand... > `show tables;` y ver que no muestre errores. En este punto la DDBB está vacía.

> [!INFO]
> Desde consola se puede ejecutar: `sudo mariadb -u root -p` (pide el pass sudo y el pass de la DDBB), luego `USE universidad;` y finalmente `SHOW TABLES;` para ver las tablas.
> Para salir se puede poner `EXIT;`

### 2. Crear las tablas

1. Ir a Databases > jdbc > click derecho > Execute comand...
2. Copiar y pegar el script SQL:

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

-- Insertar datos de prueba
delete from materia;
insert into materia (idmateria,nombre) values (1,"Matematica");
insert into materia (idmateria,nombre) values (2,"Programacion");
insert into materia (idmateria,nombre) values (3,"Frontend");
insert into materia (idmateria,nombre) values (4,"Backend");
select * from materia;
```

## Añadir GlassFish a NetBeans

1. Ir a Services > Servers > Botón derecho > Add Server
2. ![alt text](image-8.png)
3. ![alt text](image-9.png)
4. ![alt text](image-10.png)

## Configurar Glassfish para MariaDB

1. Descargar `mariadb-java-client-3.5.6.jar`: https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client/3.5.6. Antes del XML para Maven dice Files y ahí está en el link para descargar el Jar.
2. Copiar el Jar a la carpeta de extensiones de GlassFish: `/home/martin/glassfish/glassfish/domains/domain1/lib/`
3. Reiniciar o Iniciar GlassFish: Desde Netbeans > Services > Servers > Botón derecho sobre Glassfish > Restart.
4. Abrir la consola de Glassfish desde Netbeans > Services > Servers > Botón derecho sobre Glassfish > View Admin Console.

## Crear el Pool (JDBC Connection Pool)

1. Ir a Resources > JDBC > JDBC Connection Pools, y crear uno nuevo.
2. Completar los siguientes campos:
  - Pool Name: `AlumnosAppPool`
  - Resource Type: `javax.sql.DataSource`
  - Database Vendor: `MySQL`
  - Darle a Next.
  - Durante la creación del Pool no se puede cambiar el Datasource Classname, dejar `com.mysql.jdbc.jdbc2.optional.MysqlDataSource`
  - Editar el Pool y modificar el Datasource Classname: `org.mariadb.jdbc.MariaDbDataSource`
3. Añadir las siguientes propiedades:
  - url: `jdbc:mariadb://localhost:3306/alumnosapp`
  - user: `root`
  - password: `admin1234` (el pass de MariaDB)

En este punto se puede probar la conexión entre Glassfish y MariaDB, desde el Pool > Ping.

## Crear el JDNI (JDBC Resource)

Luego de crear el Pool, hay que crear el JDNI:

1. Ir a Resources > JDBC > JDBC Resources > New:
2. Darle el nombre al JDNI, por ejemplo `jdbc/alumnosapp`.
3. Seleccionar el Pool creado antes.

> [!INFO]
> El JDNI se utiliza cuando se configura la unidad de persistencia en `persistence.xml`.

---

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

## Configurar persistence.xml

Ahora hay que configurar la unidad de persistencia, para que utilice el JDNI creado en Glassfish.

> [!INFO]
> En lugar de hacerlo a mano, siguiendo el siguiente orden, el `persistence.xml` se genera automáticamente. Primero borrar el `persistence.xml` (si se hizo lo del glassfish-resources.xml, también borrarlo). Luego el Pool en el servidor, luego el DataSource, y finalmente al crear las entidades, se vuelve a generar el `persistence.xml`.

En Files > `src/main/resources/META-INF/persistence.xml`:

```xml
<persistence version="3.0"
             xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="AlumnosAppPU" transaction-type="JTA">
    <jta-data-source>jdbc/alumnosapp</jta-data-source>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties/>
  </persistence-unit>
</persistence>
```

## Crear la entidad Java para tabla Facultad

- Boton derecho sobre el proyecto > New > Entity Classes from Database

![alt text](image-6.png)

- Hay que seleccionar Local Data Source y luego `jdbc:mariadb://localhost:3306/alumnosapp`.
- Cuando carga las tablas, darle a `Add all` > Next > Next > tildar `Fully ...` y `Attributes ...` y darle a Finish.

Esto va a generar las siguientes entidades:

Facultad, Carrera, Alumnos, Examen, Materia

![alt text](image-7.png)

Además de las entidades para las tablas, se ha creado una entidad más, llamada **ExamenPK**, esto se debe a que la tabla **Examen** contiene **dos PK**, uno hacia **Alumno** y otro hacia **Materia**.

## Probar listar las Materias

Hay que crear el jsp que las muestra, pero también el controlador (servlet) que obtiene los datos de la entidad (modelo).

> [!WARNING]
> Hay que editar el package de las entidades, de acuerdo al package generado.

En Source Package > New > Servlet:

```java
// servlet controller/MateriaServlet
package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import jakarta.persistence.*; // Esto es requerido para el PersistenceContext
import java.util.List; // Esto es requerido para usar List
import com.mypackage.Materia; // Este es el import de la entidad Materia, cambiar segun el paquete generado


// MUY IMPORTANTE, el WebServlet es la URL que va a ejecutar este Servlet. El name es para configurar el web.xml
// WebServlet(name = "MateriaServlet", urlPatterns = {"/","/index","/materias"})
@WebServlet("/")
public class MateriaServlet extends HttpServlet {
    // Hay que editar src/main/resource/META-INF/persistence.xml para darle el nombre a la Unidad de Persistencia
    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> materias = em.createQuery("SELECT m FROM Materia m", Materia.class).getResultList();
        request.setAttribute("materias", materias);
        // OJO, aqui hay que cambiar el nombre del JSP si es necesario
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
```

> [!INFO]
> Otra opción para configurar el servlet es usar el web.xml en lugar de la anotación `@WebServlet`.


```xml
<servlet>
    <servlet-name>MateriaServlet</servlet-name>
    <servlet-class>controller.MateriaServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>MateriaServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

```java
// src/main/webapp/materias.jsp
// Lo siguiente es el import para la entidad Materia, hay que corregir segun el paquete utilizado.
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

## Ejecutar el proyecto

Si muestra la tabla con las materias, todo esta funcionando perfectamente.

---

## CRUDs necesarios

Implementar las vistas y formularios necesarios para registrar y consultar
datos de las entidades principales del sistema. Esto incluye dotar de
funcionalidad a las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) de
Alumno, Materia y Carrera.

## CRUD de Alumno

### Arquitectura de la aplicación

La arquitectura que se utiliza es el patrón MVC pero añadiendo una capa extra llamada gestor DAO para la persistencia de los datos.

- Modelo: Son las clases que van a dar lugar a las Entidades que reflejan a las tablas de la BBDD (creadas automáticamente por Netbeans).
- Vista: Páginas JSP para mostrar los datos y formularios.
- Controlador: Servlets que manejan las peticiones del usuario y coordinan entre la vista y el modelo.
- Gestor DAO: Clases Java que encapsulan la lógica de acceso a datos utilizando JPA. Estas clases se encargan de realizar las operaciones CRUD sobre las entidades.

El servlet importa tanto al gestor como al modelo.
El gestor importa al modelo y a JPA (jakarta.persistence.*).
El modelo no importa nada.

Flujo de llamadas
1. Servlet recibe la petición del usuario.
2. Servlet llama a métodos del Gestor/DAO para obtener o modificar datos.
3. Gestor/DAO realiza la operación en la base de datos y devuelve objetos del Modelo al Servlet.
4. Servlet pasa esos objetos a la Vista (JSP) para mostrarlos.

![alt text](assets/Arquitectura.svg)

### EJBs

Los EJBs (Enterprise Java Beans) se utilizan para gestionar la lógica de negocio y la persistencia de datos. Un EJB es un componente que corre en el servidor de aplicaciones (como GlassFish) y permite manejar transacciones, seguridad, concurrencia y otras características empresariales de forma automática.

Cuando en el código de los gestores DAO se coloca la anotación `@Stateless` indica que la clase es un EJB de tipo "stateless" (sin estado). Un EJB stateless no guarda información entre llamadas de los clientes; cada vez que se invoca un método, el servidor puede usar cualquier instancia disponible.

Al poner `@Stateless`, el servidor de aplicaciones (GlassFish) detecta la clase como un EJB y la gestiona automáticamente: crea instancias, las inyecta donde se necesiten (por ejemplo, con `@Inject` o `@EJB` en los servlets), y les da acceso a recursos como el EntityManager para la persistencia.

> [!INFO]
> El uso de `@Inject` o `@EJB` depende de si solo se usan EJBs o si también se usan CDI Beans. En este caso, como solo se usan EJBs, es más correcto usar `@EJB` para inyectar los gestores DAO en el servlet.
> `@Inject` necesita el import de `jakarta.inject.Inject`
> `@EJB` necesita el import de `jakarta.ejb.EJB`

Los tipos de EJBs más comunes son:
- `@Stateless`: No mantienen estado entre llamadas. Son ideales para operaciones que no dependen de datos previos.
- `@Stateful`: Mantienen estado entre llamadas. Útiles cuando se necesita conservar información específica del cliente.
- `@Singleton`: Una única instancia compartida por todos los clientes. Útil para recursos compartidos.

Además existen los Message-Driven Beans (`@MessageDriven`) que son usados para manejar mensajes asíncronos.

En este caso, al usar `@Stateless` en los gestores DAO, se indica que estas clases no mantienen estado entre llamadas y pueden ser reutilizadas por múltiples clientes de forma concurrente. Esto es adecuado para operaciones CRUD donde cada llamada es independiente.

### Unidad de persistencia, JDNI, JDBC y PersistenceContext

La directiva `@PersistenceContext` se utiliza para inyectar automáticamente un objeto EntityManager en la clase, que es el encargado de gestionar la conexión y las operaciones con la base de datos usando JPA.

Esta directiva le indica a GlassFish que debe buscar la unidad de persistencia definida en el archivo `persistence.xml` (en este caso llamada "AlumnosAppPU") y utilizar el DataSource configurado en GlassFish (a través del JDNI `jdbc/alumnosapp`) para establecer la conexión con la base de datos.

### Modelo Alumno

El modelo es la clase JPA creada anteriormente por Netbeans al seleccionar la opción "Entity Classes from Database".

### Gestor AlumnoDAO

```java
package database;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.mycompany.alumnosapp.Alumno;

@Stateless // Indica que es un EJB stateless
public class AlumnoDAO {
    @PersistenceContext(unitName = "AlumnosAppPU") // Inyecta el EntityManager para la unidad de persistencia
    private EntityManager em;

    public void crear(Alumno alumno) {
        em.persist(alumno); // JTA gestiona la transacción
    }

    public Alumno buscarPorId(int id) {
        return em.find(Alumno.class, id);
    }

    public List<Alumno> listarTodos() {
        return em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
    }

    public void actualizar(Alumno alumno) {
        em.merge(alumno); // JTA gestiona la transacción
    }

    public void eliminar(int id) {
        Alumno alumno = em.find(Alumno.class, id);
        if (alumno != null) {
            em.remove(alumno); // JTA gestiona la transacción
        }
    }
}
```

### Gestor CarreraDAO

```java
package database;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.mycompany.alumnosapp.Carrera;

@Stateless
public class CarreraDAO {
    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    public List<Carrera> listarTodos() {
        return em.createNamedQuery("Carrera.findAll", Carrera.class).getResultList();
    }

    public Carrera buscarPorId(int id) {
        return em.find(Carrera.class, id);
    }
}
```

### Controlador AlumnoServlet

```java
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.inject.Inject;
import database.AlumnoDAO; // Importar el gestor AlumnoDAO (ahora EJB)
import database.CarreraDAO; // Importar el gestor CarreraDAO
import com.mycompany.alumnosapp.Alumno; // Importar la entidad Alumno
import com.mycompany.alumnosapp.Carrera; // Importar la entidad Carrera

@WebServlet("/alumno")
public class AlumnoServlet extends HttpServlet {
    @EJB
    private AlumnoDAO alumnoDao;

    @EJB
    private CarreraDAO carreraDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listar todos los alumnos
        List<Alumno> alumnos = alumnoDao.listarTodos();
        request.setAttribute("alumnos", alumnos);
        // Obtener carreras para la lista desplegable
        List<Carrera> carreras = carreraDao.listarTodos();
        request.setAttribute("carreras", carreras);
        RequestDispatcher rd = request.getRequestDispatcher("alumno.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String registro = request.getParameter("registro");
        String fkIdcarreraStr = request.getParameter("fkIdcarrera");
        int fkIdcarrera = 0;
        try {
            fkIdcarrera = Integer.parseInt(fkIdcarreraStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrera inválida");
            return;
        }
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setRegistro(registro);
        // Buscar la carrera seleccionada y asignarla
        Carrera carrera = carreraDao.buscarPorId(fkIdcarrera);
        alumno.setFkIdcarrera(carrera);
        alumnoDao.crear(alumno);
        response.sendRedirect("alumno"); // Redirige para ver la lista actualizada
    }
}
```

### Vista 

```jsp
<%@ page import="com.mycompany.alumnosapp.Alumno" %>
<%@ page import="com.mycompany.alumnosapp.Carrera" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Alumnos</title>
</head>
<body>
    <h1>Lista de Alumnos</h1>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Registro</th></tr>
        <%
            List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
            for (Alumno a : alumnos) {
        %>
        <tr>
            <td><%= a.getIdalumno() %></td>
            <td><%= a.getNombre() %></td>
            <td><%= a.getRegistro() %></td>
        </tr>
        <% } %>
    </table>

    <h2>Agregar Alumno</h2>
    <form method="post" action="alumno">
        Nombre: <input type="text" name="nombre" required><br>
        Registro: <input type="text" name="registro" required><br>
        Carrera:
        <select name="fkIdcarrera" required>
            <% List<Carrera> carreras = (List<Carrera>) request.getAttribute("carreras");
               for (Carrera c : carreras) { %>
                <option value="<%= c.getIdcarrera() %>"><%= c.getNombre() %></option>
            <% } %>
        </select><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>
```

---

## Migrando a Facades los gestores DAO

No se pueden migrar de forma directa, sino que hay que crear los Facades y dejar de usar los Gestores DAO.

Para crear los Facades hay que:

1. Botón derecho sobre el proyecto > New > Other > Enterprise JavaBeans > Session Beans For Entity Classes
2. Seleccionar las entidades a crear los Facades (Alumno, Carrera, Materia, Facultad, Examen) con Add All.
3. Editar el package si es necesario.
4. Finalizar.

Los gestores DAO hechos a mano y los Facades generados por NetBeans cumplen la misma función: encapsulan la lógica CRUD para una entidad. La diferencia principal es que los Facades extienden de una clase base llamada `AbstractFacade<T>`, mientras que los DAOs hechos a mano no.

**¿Qué aporta `AbstractFacade<T>`?**

Es una clase genérica que implementa los métodos CRUD básicos (create, edit, remove, find, findAll, etc.) usando JPA.
Permite reutilizar el mismo código para todas las entidades, evitando duplicación.
El Facade concreto (por ejemplo, AlumnoFacade) solo necesita indicar la entidad y el EntityManager, heredando toda la funcionalidad CRUD.

Toda la lógica colocada en los gestores DAO pierde sentido, ya no es necesario los métodos para listar, actualizar y eliminar que se codificaron en los gestores DAO, ya que el AbstractFacade los implementa en create(), find(), edit(), remove(), findAll(), etc.

## Usando los Facades en el Servlet AlumnoServlet

```java
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.AlumnoFacade;   // Importa el Facade generado
import database.CarreraFacade; // Importa el Facade generado
import com.mycompany.alumnosapp.Alumno;
import com.mycompany.alumnosapp.Carrera;

@WebServlet("/alumno")
public class AlumnoServlet extends HttpServlet {
    @EJB
    private AlumnoFacade alumnoFacade;

    @EJB
    private CarreraFacade carreraFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listar todos los alumnos usando el Facade
        List<Alumno> alumnos = alumnoFacade.findAll();
        request.setAttribute("alumnos", alumnos);

        // Obtener carreras para la lista desplegable usando el Facade
        List<Carrera> carreras = carreraFacade.findAll();
        request.setAttribute("carreras", carreras);

        RequestDispatcher rd = request.getRequestDispatcher("alumno.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            String idStr = request.getParameter("idalumno");
            try {
                int id = Integer.parseInt(idStr);
                Alumno alumno = alumnoFacade.find(id);
                if (alumno != null) {
                    alumnoFacade.remove(alumno);
                }
            } catch (NumberFormatException e) {
                // Manejar error si el id no es válido
            }
            response.sendRedirect("alumno");
            return;
        } else if ("agregar".equals(accion) || accion == null) {
            // Si no se envía "accion", se asume agregar por compatibilidad con el formulario actual
            String nombre = request.getParameter("nombre");
            String registro = request.getParameter("registro");
            String fkIdcarreraStr = request.getParameter("fkIdcarrera");
            int fkIdcarrera = 0;
            try {
                fkIdcarrera = Integer.parseInt(fkIdcarreraStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrera inválida");
                return;
            }
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setRegistro(registro);

            Carrera carrera = carreraFacade.find(fkIdcarrera);
            alumno.setFkIdcarrera(carrera);

            alumnoFacade.create(alumno);
            response.sendRedirect("alumno");
            return;
        }
        // Aquí puedes añadir más acciones en el futuro, por ejemplo:
        // else if ("actualizar".equals(accion)) { ... }
    }
}
```

## Mostrando mas información en la vista Alumno.jsp

```jsp
<%@ page import="com.mycompany.alumnosapp.Alumno" %>
<%@ page import="com.mycompany.alumnosapp.Carrera" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Alumnos</title>
</head>
<body>
    <h1>Lista de Alumnos</h1>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Registro</th><th>Carrera</th><th>Acciones</th></tr>
        <%
            List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
            for (Alumno a : alumnos) {
        %>
        <tr>
            <td><%= a.getIdalumno() %></td>
            <td><%= a.getNombre() %></td>
            <td><%= a.getRegistro() %></td>
            <td><%= a.getFkIdcarrera().getNombre() %></td>
            <td>
                <form method="post" action="alumno" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="idalumno" value="<%= a.getIdalumno() %>">
                    <input type="submit" value="Eliminar" onclick="return confirm('¿Seguro que desea eliminar este alumno?');">
                </form>
            </td>
        </tr>
        <% } %>
    </table>

    <h2>Agregar Alumno</h2>
    <form method="post" action="alumno">
        <input type="hidden" name="accion" value="agregar">
        Nombre: <input type="text" name="nombre" required><br>
        Registro: <input type="text" name="registro" required><br>
        Carrera:
        <select name="fkIdcarrera" required>
            <% List<Carrera> carreras = (List<Carrera>) request.getAttribute("carreras");
               for (Carrera c : carreras) { %>
                <option value="<%= c.getIdcarrera() %>"><%= c.getNombre() %></option>
            <% } %>
        </select><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>
```

## Añadir JSTL al proyecto Maven

1. Ir a: <https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api/2.0.0>
2. Copiar el codigo para Maven:

```xml
<!-- https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api -->
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>2.0.0</version>
</dependency>
```
3. Abrir el `Project File/pom.xml` y añadir en `<dependencies />`.
4. Correr el proyecto (con esto, la primera vez, se va a descargar la dependencia).

## Modificar Alumno.jsp para usar JSTL

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mycompany.alumnosapp.Alumno" %>
<%@ page import="com.mycompany.alumnosapp.Carrera" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Alumnos</title>
</head>
<body>
    <h1>Lista de Alumnos</h1>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Registro</th><th>Carrera</th><th>Acciones</th></tr>
        <c:forEach var="a" items="${alumnos}">
            <tr>
                <td>${a.idalumno}</td>
                <td>${a.nombre}</td>
                <td>${a.registro}</td>
                <td>${a.fkIdcarrera.nombre}</td>
                <td>
                    <form method="post" action="alumno" style="display:inline;">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="idalumno" value="${a.idalumno}">
                        <input type="submit" value="Eliminar" onclick="return confirm('¿Seguro que desea eliminar este alumno?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar Alumno</h2>
    <form method="post" action="alumno">
        <input type="hidden" name="accion" value="agregar">
        Nombre: <input type="text" name="nombre" required><br>
        Registro: <input type="text" name="registro" required><br>
        Carrera:
        <select name="fkIdcarrera" required>
            <c:forEach var="c" items="${carreras}">
                <option value="${c.idcarrera}">${c.nombre}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>
```

---

## Modificando el index.jsp para Facultad

En este ejercicio se busca que el menú principal de la aplicación (por ejemplo, en `index.jsp`) muestre dinámicamente todas las facultades almacenadas en la base de datos. Así, si se agregan o eliminan facultades, el menú se actualiza automáticamente.

Si se ha configurado el web.xml, primero hay que eliminar esa configuracion, para luego crear el manejador principal.

Para no tener que configurar el web.xml, se puede usar la anotación `@WebServlet` en el servlet principal.

```java
@WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/", "/index", "/facultad"})
```

La urlPatterns es la que determina que este servlet se ejecute al ingresar a la raíz `/` o a `/index`, y también cuando se seleccione una facultad del menú, que enviará la petición a `/facultad?facultadId=...`.

De esa forma, 

### ¿Qué hay que hacer?

1. **Preparar la lista de facultades al iniciar la aplicación**
   - Cuando el servidor inicia, el servlet principal (por ejemplo, llamado `Manejador`) debe consultar todas las facultades usando el Facade correspondiente (`FacultadFacade`).
   - Esa lista se guarda como atributo en el contexto de la aplicación, para que esté disponible en cualquier JSP.

   Ejemplo de código para el servlet:
   ```java
   package controller;

   import jakarta.servlet.annotation.WebServlet;
   import jakarta.servlet.http.HttpServlet;
   import jakarta.servlet.ServletException;
   import jakarta.ejb.EJB;

   @WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/agregar", "/listar", "/facultad"})
   public class Manejador extends HttpServlet {
       @EJB
       private database.FacultadFacade facultadFacade;

       @Override
       public void init() throws ServletException {
           // Almacena la lista de facultades en el contexto global de la aplicación
           getServletContext().setAttribute("facultades", facultadFacade.findAll());
       }
   }
   ```
   > El método `init()` se ejecuta una sola vez al iniciar la aplicación. Así, la lista de facultades estará disponible para todos los usuarios.

2. **Mostrar el menú dinámico en el JSP**
   - En el archivo `index.jsp`, usa JSTL para recorrer la lista de facultades y mostrar cada una como un ítem del menú.
   - Así, el menú se genera automáticamente según los datos de la base.

   Ejemplo de código para el menú:
   ```jsp
   <li><a href="#">
       <p class="opcion">Facultades</p>
   </a>
   <ul>
       <c:forEach var="f" items="${facultades}">
           <li><a href="facultad?facultadId=${f.idfacultad}">${f.nombre}</a></li>
       </c:forEach>
   </ul>
   </li>
   ```
   > Recuerda importar la librería JSTL en el JSP: `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`

3. **Capturar la selección de facultad**
   - Cuando el usuario hace clic en una facultad del menú, se envía una petición a la URL `/facultad?facultadId=...`.
   - El servlet `Manejador` debe tener mapeada la URL `/facultad` para recibir ese parámetro y mostrar la información correspondiente.

   Ejemplo de mapeo:
   ```java
   @WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/agregar", "/listar", "/facultad"})
   ```
   > En el método `doGet` del servlet, puedes obtener el parámetro con `request.getParameter("facultadId")` y mostrar la información de la facultad seleccionada.

### Menu de facultades y lista de carreras

Cuando el usuario selecciona una facultad del menú, se hace la peticion: `http://localhost:8080/AlumnosApp/facultad?facultadId=1`.

1. Añadir doGet al Manejador:

```java
package controller;
   
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Si usas entidades:
import com.mycompany.alumnosapp.Carrera;
import com.mycompany.alumnosapp.Facultad;

// Si usas el Facade:
import database.CarreraFacade;
import database.FacultadFacade;

@WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/agregar", "/listar", "/facultad"})
public class Manejador extends HttpServlet {
    @EJB
    private FacultadFacade facultadFacade;
    @EJB
    private CarreraFacade carreraFacade;

    @Override
    public void init() throws ServletException {
        // Almacena la lista de facultades en el contexto global de la aplicación
        getServletContext().setAttribute("facultades", facultadFacade.findAll());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/facultad".equals(path)) {
            String facultadIdStr = request.getParameter("facultadId");
            try {
                int facultadId = Integer.parseInt(facultadIdStr);
                Facultad facultad = facultadFacade.find(facultadId);
                List<Carrera> carreras = carreraFacade.findByFacultad(facultad); // Este método lo puedes implementar en CarreraFacade
                request.setAttribute("facultad", facultad);
                request.setAttribute("carreras", carreras);
                request.getRequestDispatcher("facultad.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Error: " + e.getMessage());
                request.getRequestDispatcher("facultad.jsp").forward(request, response);
            }
        }
        // ...otros casos...
    }
}
```

2. Al CarreraFacade hay que añadirle el método findByFacultad:

```java
// Otros imports
import java.util.List;

@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {
    // Otros métodos...

    public List<Carrera> findByFacultad(Facultad facultad) {
        return em.createQuery("SELECT c FROM Carrera c WHERE c.fkIdfacultad = :facultad", Carrera.class)
                .setParameter("facultad", facultad)
                .getResultList();
    }
}
```

3. Crear el JSP facultad.jsp para mostrar las carreras de la facultad seleccionada:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Carreras de la Facultad</title>
</head>
<body>
    <h1>Carreras disponibles en la facultad: ${facultad.nombre}</h1>
    <ul>
        <c:forEach var="c" items="${carreras}">
            <li>${c.nombre}</li>
        </c:forEach>
    </ul>
    <a href="index.jsp">Volver al menú principal</a>
</body>
</html>
```

## Modificar la carga de alumnos para añadir la carrera por medio de un select y c:forEach

Esto ya se hizo.

## Añadir la entidad Docente

Los docentes tienen nombre, cargo y materias que dictan (relación muchos a muchos).

Script SQL para docentes:

```sql
CREATE TABLE Docente (
    iddocente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL
);

CREATE TABLE Docente_Materia (
    fk_iddocente INT,
    fk_idmateria INT,
    PRIMARY KEY (fk_iddocente, fk_idmateria),
    FOREIGN KEY (fk_iddocente) REFERENCES Docente(iddocente),
    FOREIGN KEY (fk_idmateria) REFERENCES Materia(idmateria)
);

-- Insertar algunos docentes de ejemplo
INSERT INTO Docente (nombre, cargo) VALUES
('Juan Perez', 'Profesor Titular'),
('Maria Gomez', 'Profesor Asociado'),
('Carlos Ruiz', 'Profesor Adjunto');

-- Asignar materias a los docentes
INSERT INTO Docente_Materia (fk_iddocente, fk_idmateria) VALUES
(1, 1), -- Juan Perez dicta la materia con id 1
(1, 2), -- Juan Perez dicta la materia con id 2
(2, 2), -- Maria Gomez dicta la materia con id 2
(2, 3), -- Maria Gomez dicta la materia con id 3
(3, 1); -- Carlos Ruiz dicta la materia con id 1
```

Ahora toca crear el Session Bean para generar las entidades y el Facade.

1. New > Entity Classes from Database
2. New > Session Beans For Entity Classes

Ahora toca crear el servlet y la vista para listar los docentes y las materias que dictan.

```java
// DocenteServlet.java
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.DocenteFacade; // El Facade generado por NetBeans
import com.mycompany.alumnosapp.Docente; // La entidad Docente

@WebServlet("/docentes")
public class DocenteServlet extends HttpServlet {
    @EJB
    private DocenteFacade docenteFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Docente> docentes = docenteFacade.findAll();
        request.setAttribute("docentes", docentes);
        RequestDispatcher rd = request.getRequestDispatcher("docentes.jsp");
        rd.forward(request, response);
    }
}
```

```jsp
<!-- docentes.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Docentes y Materias</title>
</head>
<body>
    <h1>Docentes y las materias que dictan</h1>
    <table border="1">
        <tr>
            <th>Docente</th>
            <th>Cargo</th>
            <th>Materias que dicta</th>
        </tr>
        <c:forEach var="d" items="${docentes}">
            <tr>
                <td>${d.nombre}</td>
                <td>${d.cargo}</td>
                <td>
                    <c:forEach var="m" items="${d.materiaCollection}">
                        ${m.nombre}<br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

## Ultimas funcionalidades

1. Listar las mesas de examen del turno de julio, mostrando para cada una los datos del alumno: nombre y nota obtenida.
2. Obtener un listado de los alumnos que no hayan rendido ninguna materia durante el año en curso.
3. Mostrar los nombres de los docentes que dictan más de dos materias.

ScriptSQL:

```sql
-- Eliminar datos respetando las claves foráneas
DELETE FROM Docente_Materia;
DELETE FROM examen;
DELETE FROM alumno;
DELETE FROM Docente;
DELETE FROM carrera;
DELETE FROM facultad;
DELETE FROM materia;

-- Facultades
INSERT INTO facultad (idfacultad, nombre) VALUES
(1, 'FCEFyN'),
(2, 'FAD');

-- Carreras
INSERT INTO carrera (idcarrera, nombre, fk_idfacultad) VALUES
(1, 'LCC', 1),
(2, 'LSI', 1),
(3, 'TUPW', 1),
(4, 'Arq', 2),
(5, 'DG', 2);

-- Materias (2 por carrera)
INSERT INTO materia (idmateria, nombre) VALUES
(1, 'Matemática Discreta'),
(2, 'Programación I'),
(3, 'Álgebra'),
(4, 'Sistemas Operativos'),
(5, 'Web I'),
(6, 'Bases de Datos'),
(7, 'Historia de la Arquitectura'),
(8, 'Diseño Asistido'),
(9, 'Teoría del Color'),
(10, 'Tipografía');

-- Alumnos (al menos uno por carrera, y uno sin exámenes en 2025)
INSERT INTO alumno (idalumno, nombre, registro, fk_idcarrera) VALUES
(1, 'Juan Perez', 'J00001', 1), -- LCC
(2, 'Maria Gomez', 'M00002', 2), -- LSI
(3, 'Carlos Ruiz', 'C00003', 3), -- TUPW
(4, 'Ana Torres', 'A12345', 4), -- Arq
(5, 'Luis Fernández', 'L54321', 5), -- DG
(6, 'Sofía Martínez', 'S11111', 1), -- LCC (sin exámenes en 2025)
(7, 'Pedro Alvarez', 'P22222', 2), -- LSI
(8, 'Lucía Romero', 'L33333', 3), -- TUPW
(9, 'Martín Castro', 'M44444', 4), -- Arq
(10, 'Valeria Ruiz', 'V55555', 5); -- DG

-- Exámenes (al menos un alumno sin exámenes en 2025: Sofía Martínez)
INSERT INTO examen (fk_idmateria, fk_idalumno, fecha, nota) VALUES
(1, 1, '2025-07-10 10:00:00', 8), -- Matemática Discreta, Juan Perez
(2, 1, '2025-07-12 09:00:00', 7), -- Programación I, Juan Perez
(3, 2, '2025-07-15 11:00:00', 6), -- Álgebra, Maria Gomez
(4, 2, '2025-07-20 09:00:00', 9), -- Sistemas Operativos, Maria Gomez
(5, 3, '2025-07-22 10:00:00', 8), -- Web I, Carlos Ruiz
(6, 3, '2025-07-25 11:00:00', 7), -- Bases de Datos, Carlos Ruiz
(7, 4, '2025-07-28 09:00:00', 6), -- Historia de la Arquitectura, Ana Torres
(8, 4, '2025-07-30 10:00:00', 8), -- Diseño Asistido, Ana Torres
(9, 5, '2025-07-31 11:00:00', 7), -- Teoría del Color, Luis Fernández
(10, 5, '2025-07-31 12:00:00', 9), -- Tipografía, Luis Fernández
(1, 7, '2025-07-10 10:00:00', 6), -- Matemática Discreta, Pedro Alvarez
(2, 8, '2025-07-12 09:00:00', 8), -- Programación I, Lucía Romero
(3, 9, '2025-07-15 11:00:00', 7), -- Álgebra, Martín Castro
(4, 10, '2025-07-20 09:00:00', 8); -- Sistemas Operativos, Valeria Ruiz

-- Docentes (al menos dos dictan más de dos materias)
INSERT INTO Docente (iddocente, nombre, cargo) VALUES
(1, 'Sergio López', 'Profesor Titular'),
(2, 'Marta Díaz', 'Profesor Asociado'),
(3, 'Javier Gómez', 'Profesor Adjunto'),
(4, 'Laura Fernández', 'Profesor Titular');

-- Docente_Materia (asignar materias, al menos dos docentes en más de 2 materias)
INSERT INTO Docente_Materia (fk_iddocente, fk_idmateria) VALUES
(1, 1), -- Sergio López dicta Matemática Discreta
(1, 2), -- Sergio López dicta Programación I
(1, 3), -- Sergio López dicta Álgebra
(1, 4), -- Sergio López dicta Sistemas Operativos
(2, 5), -- Marta Díaz dicta Web I
(2, 6), -- Marta Díaz dicta Bases de Datos
(2, 1), -- Marta Díaz dicta Matemática Discreta
(3, 7), -- Javier Gómez dicta Historia de la Arquitectura
(3, 8), -- Javier Gómez dicta Diseño Asistido
(4, 9), -- Laura Fernández dicta Teoría del Color
(4, 10), -- Laura Fernández dicta Tipografía
(4, 5), -- Laura Fernández dicta Web I
(4, 6); -- Laura Fernández dicta Bases de Datos
```

Añadir las consultas a los facades:

```java
import java.util.List;

// En ExamenFacade
public List<Object[]> listarMesasJulio() {
    // Usar BETWEEN para filtrar por fechas en JPQL (EclipseLink no soporta MONTH/YEAR)
    return em.createQuery(
        "SELECT e, a FROM Examen e JOIN e.alumno a WHERE e.fecha BETWEEN :inicio AND :fin"
    )
    .setParameter("inicio", java.sql.Timestamp.valueOf("2025-07-01 00:00:00"))
    .setParameter("fin", java.sql.Timestamp.valueOf("2025-07-31 23:59:59"))
    .getResultList();
}

// En AlumnoFacade
public List<Alumno> alumnosSinExamenes2025() {
    // YEAR() tampoco es estándar JPQL, para máxima compatibilidad filtra por rango de fechas
    // Aquí se deja como ejemplo, pero si da error, usa BETWEEN como arriba
    return em.createQuery(
        "SELECT a FROM Alumno a WHERE a.idalumno NOT IN (SELECT DISTINCT e.alumno.idalumno FROM Examen e WHERE e.fecha BETWEEN :inicio AND :fin)"
    )
    .setParameter("inicio", java.sql.Timestamp.valueOf("2025-01-01 00:00:00"))
    .setParameter("fin", java.sql.Timestamp.valueOf("2025-12-31 23:59:59"))
    .getResultList();
}

// En DocenteFacade
public List<Docente> docentesMasDeDosMaterias() {
    return em.createQuery(
        "SELECT d FROM Docente d WHERE SIZE(d.materiaCollection) > 2"
    ).getResultList();
}
```

ConsultasServlet.java


```java
// ConsultasServlet.java
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.ExamenFacade;
import database.AlumnoFacade;
import database.DocenteFacade;
import com.mycompany.alumnosapp.Examen;
import com.mycompany.alumnosapp.Alumno;
import com.mycompany.alumnosapp.Docente;

@WebServlet("/consultas")
public class ConsultasServlet extends HttpServlet {
    @EJB
    private ExamenFacade examenFacade;
    @EJB
    private AlumnoFacade alumnoFacade;
    @EJB
    private DocenteFacade docenteFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Listar mesas de examen del turno de julio
        List<Object[]> mesasJulio = examenFacade.listarMesasJulio();
        request.setAttribute("mesasJulio", mesasJulio);

        // 2. Alumnos sin examenes en 2025
        List<Alumno> alumnosSinExamenes = alumnoFacade.alumnosSinExamenes2025();
        request.setAttribute("alumnosSinExamenes", alumnosSinExamenes);

        // 3. Docentes con más de dos materias
        List<Docente> docentesMasDeDosMaterias = docenteFacade.docentesMasDeDosMaterias();
        request.setAttribute("docentesMasDeDosMaterias", docentesMasDeDosMaterias);

        RequestDispatcher rd = request.getRequestDispatcher("consultas.jsp");
        rd.forward(request, response);
    }
}
```

```jsp
<!-- consultas.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Consultas Especiales</title>
</head>
<body>
    <h1>Mesas de examen del turno de julio</h1>
    <table border="1">
        <tr>
            <th>Materia</th>
            <th>Alumno</th>
            <th>Nota</th>
            <th>Fecha</th>
        </tr>
        <c:forEach var="row" items="${mesasJulio}">
            <tr>
                <td>${row[0].materia.nombre}</td>
                <td>${row[1].nombre}</td>
                <td>${row[0].nota}</td>
                <td>${row[0].fecha}</td>
            </tr>
        </c:forEach>
    </table>

    <h1>Alumnos que no rindieron ninguna materia en 2025</h1>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Registro</th>
        </tr>
        <c:forEach var="a" items="${alumnosSinExamenes}">
            <tr>
                <td>${a.nombre}</td>
                <td>${a.registro}</td>
            </tr>
        </c:forEach>
    </table>

    <h1>Docentes que dictan más de dos materias</h1>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Cargo</th>
            <th>Cantidad de materias</th>
        </tr>
        <c:forEach var="d" items="${docentesMasDeDosMaterias}">
            <tr>
                <td>${d.nombre}</td>
                <td>${d.cargo}</td>
                <td>${fn:length(d.materiaCollection)}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

## Cargando el Proyecto en Linux

1. Configurar MariaDB:

```sh
sudo mariadb -u root
ALTER USER 'root'@'localhost' IDENTIFIED BY 'admin1234';
FLUSH PRIVILEGES;
EXIT;
```

2. Crear la base de datos:

```sh
sudo mariadb -u root -p
```

3. El Script SQL completo y actualizado es:

```sql
-- Crear la base de datos
DROP DATABASE IF EXISTS alumnosapp;
CREATE DATABASE IF NOT EXISTS alumnosapp;
USE alumnosapp;

-- Tabla: facultad
CREATE TABLE facultad (
    idfacultad INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (idfacultad)
) ENGINE=InnoDB;

-- Tabla: carrera
CREATE TABLE carrera (
    idcarrera INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
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
    nombre VARCHAR(50) NOT NULL,
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
    nombre VARCHAR(50) NOT NULL,
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

CREATE TABLE docente (
    iddocente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL
);

CREATE TABLE docente_materia (
    fk_iddocente INT,
    fk_idmateria INT,
    PRIMARY KEY (fk_iddocente, fk_idmateria),
    FOREIGN KEY (fk_iddocente) REFERENCES docente(iddocente),
    FOREIGN KEY (fk_idmateria) REFERENCES materia(idmateria)
);

-- Eliminar datos respetando las claves foráneas
DELETE FROM docente_materia;
DELETE FROM examen;
DELETE FROM alumno;
DELETE FROM docente;
DELETE FROM carrera;
DELETE FROM facultad;
DELETE FROM materia;

-- Facultades
INSERT INTO facultad (idfacultad, nombre) VALUES
(1, 'FCEFyN'),
(2, 'FAD');

-- Carreras
INSERT INTO carrera (idcarrera, nombre, fk_idfacultad) VALUES
(1, 'LCC', 1),
(2, 'LSI', 1),
(3, 'TUPW', 1),
(4, 'Arq', 2),
(5, 'DG', 2);

-- materias (2 por carrera)
INSERT INTO materia (idmateria, nombre) VALUES
(1, 'Matemática Discreta'),
(2, 'Programación I'),
(3, 'Álgebra'),
(4, 'Sistemas Operativos'),
(5, 'Web I'),
(6, 'Bases de Datos'),
(7, 'Historia de la Arquitectura'),
(8, 'Diseño Asistido'),
(9, 'Teoría del Color'),
(10, 'Tipografía');

-- Alumnos (al menos uno por carrera, y uno sin exámenes en 2025)
INSERT INTO alumno (idalumno, nombre, registro, fk_idcarrera) VALUES
(1, 'Juan Perez', 'J00001', 1), -- LCC
(2, 'Maria Gomez', 'M00002', 2), -- LSI
(3, 'Carlos Ruiz', 'C00003', 3), -- TUPW
(4, 'Ana Torres', 'A12345', 4), -- Arq
(5, 'Luis Fernández', 'L54321', 5), -- DG
(6, 'Sofía Martínez', 'S11111', 1), -- LCC (sin exámenes en 2025)
(7, 'Pedro Alvarez', 'P22222', 2), -- LSI
(8, 'Lucía Romero', 'L33333', 3), -- TUPW
(9, 'Martín Castro', 'M44444', 4), -- Arq
(10, 'Valeria Ruiz', 'V55555', 5); -- DG

-- Exámenes (al menos un alumno sin exámenes en 2025: Sofía Martínez)
INSERT INTO examen (fk_idmateria, fk_idalumno, fecha, nota) VALUES
(1, 1, '2025-07-10 10:00:00', 8), -- Matemática Discreta, Juan Perez
(2, 1, '2025-07-12 09:00:00', 7), -- Programación I, Juan Perez
(3, 2, '2025-07-15 11:00:00', 6), -- Álgebra, Maria Gomez
(4, 2, '2025-07-20 09:00:00', 9), -- Sistemas Operativos, Maria Gomez
(5, 3, '2025-07-22 10:00:00', 8), -- Web I, Carlos Ruiz
(6, 3, '2025-07-25 11:00:00', 7), -- Bases de Datos, Carlos Ruiz
(7, 4, '2025-07-28 09:00:00', 6), -- Historia de la Arquitectura, Ana Torres
(8, 4, '2025-07-30 10:00:00', 8), -- Diseño Asistido, Ana Torres
(9, 5, '2025-07-31 11:00:00', 7), -- Teoría del Color, Luis Fernández
(10, 5, '2025-07-31 12:00:00', 9), -- Tipografía, Luis Fernández
(1, 7, '2025-07-10 10:00:00', 6), -- Matemática Discreta, Pedro Alvarez
(2, 8, '2025-07-12 09:00:00', 8), -- Programación I, Lucía Romero
(3, 9, '2025-07-15 11:00:00', 7), -- Álgebra, Martín Castro
(4, 10, '2025-07-20 09:00:00', 8); -- Sistemas Operativos, Valeria Ruiz

-- docentes (al menos dos dictan más de dos materias)
INSERT INTO docente (iddocente, nombre, cargo) VALUES
(1, 'Sergio López', 'Profesor Titular'),
(2, 'Marta Díaz', 'Profesor Asociado'),
(3, 'Javier Gómez', 'Profesor Adjunto'),
(4, 'Laura Fernández', 'Profesor Titular');

-- docente_materia (asignar materias, al menos dos docentes en más de 2 materias)
INSERT INTO docente_materia (fk_iddocente, fk_idmateria) VALUES
(1, 1), -- Sergio López dicta Matemática Discreta
(1, 2), -- Sergio López dicta Programación I
(1, 3), -- Sergio López dicta Álgebra
(1, 4), -- Sergio López dicta Sistemas Operativos
(2, 5), -- Marta Díaz dicta Web I
(2, 6), -- Marta Díaz dicta Bases de Datos
(2, 1), -- Marta Díaz dicta Matemática Discreta
(3, 7), -- Javier Gómez dicta Historia de la Arquitectura
(3, 8), -- Javier Gómez dicta Diseño Asistido
(4, 9), -- Laura Fernández dicta Teoría del Color
(4, 10), -- Laura Fernández dicta Tipografía
(4, 5), -- Laura Fernández dicta Web I
(4, 6); -- Laura Fernández dicta Bases de Datos
```

3. Copiar el conector a mariadb en glassfish: `cp mariadb-java-client-3.5.6.jar ~/glassfish/glassfish/domains/domain1/lib/`

4. Reiniciar Glassfish.

5. Crear el Pool y JDBC: Todo el proyecto esta configurado para trabajar con la bbdd, pool y JDBC alumnosapp, asi que ojo con eso.

6. Probar.