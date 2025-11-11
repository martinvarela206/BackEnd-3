# Proyecto Full Backend con Maven

## 1. Crear la Base de Datos para el proyecto

```SQL
-- CREAR LA BASE DE DATOS (ELIMINAR SI YA EXISTE)
DROP DATABASE IF EXISTS inventario_lia;
CREATE DATABASE inventario_lia;
USE inventario_lia;

-- Los elementos pueden tener un nro_lia o un nro_unsj, generalmente ambos, incluso individualmente pueden repetirse, pero es muy raro, y ademas pueden ser null, pero nunca se repiten o son nulos ambos a la vez.
-- Crear tabla Elementos
CREATE TABLE elementos (
    nro_lia VARCHAR(25),
    nro_unsj VARCHAR(25),
    tipo ENUM('cpu', 'monitor', 'switch', 'router', 'impresora', 'teclado', 'mouse', 'proyector', 'otro') NOT NULL,
    descripcion VARCHAR(255),
    cantidad INT,
    CONSTRAINT pk_elementos PRIMARY KEY (nro_lia, nro_unsj)
) ENGINE=InnoDB;

-- Crear tabla Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- Crear tabla Roles
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rol ENUM('user_admin', 'coordinador', 'tecnico', 'revisor', 'invitado') NOT NULL UNIQUE
) ENGINE=InnoDB;

-- Crear tabla Usuario_Roles
CREATE TABLE usuario_roles (
    user_id INT NOT NULL,
    rol_id INT NOT NULL,
    CONSTRAINT pk_usuario_roles PRIMARY KEY (user_id, rol_id),
    CONSTRAINT fk_user_ur FOREIGN KEY (user_id) REFERENCES usuarios(id),
    CONSTRAINT fk_rol_ur FOREIGN KEY (rol_id) REFERENCES roles(id)
) ENGINE=InnoDB;

-- Movimientos necesita que ya existan las tablas elementos y usuarios
-- Crear tabla Movimientos
CREATE TABLE movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nro_lia VARCHAR(25),
    nro_unsj VARCHAR(25),
    user_id INT NOT NULL,
    estado ENUM('ingresado', 'guardado', 'funcionando', 'dado de baja', 'prestado') NOT NULL,
    ubicacion VARCHAR(100),
    fecha DATETIME NOT NULL,
    comentario VARCHAR(255),
    CONSTRAINT fk_elementos FOREIGN KEY (nro_lia, nro_unsj) REFERENCES elementos(nro_lia, nro_unsj),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES usuarios(id)
) ENGINE=InnoDB;

-- Insertar roles iniciales
INSERT INTO roles (rol) VALUES
('user_admin'),
('coordinador'),
('tecnico'),
('invitado');

-- Insertar usuario admin inicial con todos los roles
INSERT INTO usuarios (nombre, password) VALUES
('admin', '1234');
INSERT INTO usuario_roles (user_id, rol_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);

-- Crear un usuario coordinador con los permisos coordinador, tecnico e invitado
INSERT INTO usuarios (nombre, password) VALUES
('coordinador', '1234');
INSERT INTO usuario_roles (user_id, rol_id) VALUES
(2, 2),
(2, 3),
(2, 4);

-- Crear un usuario tecnico con los permisos tecnico e invitado
INSERT INTO usuarios (nombre, password) VALUES
('tecnico', '1234');
INSERT INTO usuario_roles (user_id, rol_id) VALUES
(3, 3),
(3, 4);

-- Crear un usuario revisor inicial con su rol
INSERT INTO usuarios (nombre, password) VALUES
('revisor', '1234');
INSERT INTO usuario_roles (user_id, rol_id) VALUES
(4, 4);

-- Los elementos pueden ser CPU (cuya descripcion incluye marca, modelo, ram, disco, procesador), monitor (marca, modelo, pulgadas), y otros elementos que tambien suelen tener marca y modelo son mouses, teclados, impresoras, proyectores, switches, routers, etc. 
-- Insertar ejemplos en la tabla elementos
INSERT INTO elementos (nro_lia, nro_unsj, tipo, descripcion, cantidad) VALUES
('LIA001', 'UNSJ001', 'cpu', 'CPU Dell OptiPlex 3080, RAM 8GB, Disco 1TB, Intel i5', 10),
('LIA002', 'UNSJ002', 'monitor', 'Monitor Samsung 24 pulgadas, Full HD', 5),
('LIA003', NULL, 'teclado', 'Teclado Logitech K120', 20),
(NULL, 'UNSJ003', 'mouse', 'Mouse inalámbrico Logitech M185', 15),
('LIA004', 'UNSJ004', 'impresora', 'Impresora HP LaserJet Pro M404dn', 3);


-- Los movimientos tienen la siguiente rutina. El primer movimiento siempre es un ingresado (todos los elementos deben tener al menos 1 movimiento, el de ingresado), cuando el elemento se guarda en un lugar especifico, entonces el movimiento es guardado, si se utiliza y funciona el elemento, el movimiento es funcionando, lo mismo pada dado de baja, prestado, etc. Los usuarios que pueden realizar los movimientos son todos los que tengan el rol tecnico, principalmente el usuario admin, coordinador y tecnico.
-- Las ubicaciones pueden ser actualmente las siguientes: "Lab FB", "Lab PM", "Lab Redes", "Lab Hardware", "Oficina A", "Oficina B", "Administracion", "Lab C", "Lab B", "Prestado", "Dado de baja".
-- Cuando un elemento es prestado, en comentario se indica a quien se lo presto (por ejemplo, Departamento de Informatica, Profes Emilio, Profesora Pamela, etc), y cuando es dado de baja, se indica la resolucion por la cual se le dio de baja.
-- Insertar ejemplos en la tabla movimientos
-- Todos los elementos deben tener al menos un movimiento inicial de 'ingresado'
INSERT INTO movimientos (nro_lia, nro_unsj, user_id, estado, ubicacion, fecha, comentario) VALUES
('LIA001', 'UNSJ001', 1, 'ingresado', 'Lab FB', '2025-11-09 10:00:00', 'Ingreso inicial'),
('LIA002', 'UNSJ002', 2, 'ingresado', 'Lab PM', '2025-11-09 10:30:00', 'Ingreso inicial'),
('LIA003', NULL, 3, 'ingresado', 'Lab Redes', '2025-11-09 11:00:00', 'Ingreso inicial'),
(NULL, 'UNSJ003', 4, 'ingresado', 'Lab Hardware', '2025-11-09 11:30:00', 'Ingreso inicial'),
('LIA004', 'UNSJ004', 1, 'ingresado', 'Oficina A', '2025-11-09 12:00:00', 'Ingreso inicial');

-- Mantener los movimientos adicionales para variedad de estados
INSERT INTO movimientos (nro_lia, nro_unsj, user_id, estado, ubicacion, fecha, comentario) VALUES
('LIA002', 'UNSJ002', 2, 'guardado', 'Lab PM', '2025-11-09 11:00:00', 'Guardado en el laboratorio'),
('LIA003', NULL, 3, 'funcionando', 'Lab Redes', '2025-11-09 12:00:00', 'En uso en el laboratorio de redes'),
(NULL, 'UNSJ003', 4, 'prestado', 'Prestado', '2025-11-09 13:00:00', 'Prestado al Departamento de Informática'),
('LIA004', 'UNSJ004', 1, 'dado de baja', 'Dado de baja', '2025-11-09 14:00:00', 'Resolución 123/2025 para baja de equipo');
```

## 2. Configurar GLassFish

1. Iniciar Netbeans.
2. Iniciar GlassFish desde Services > Servers.
3. Ingresar a botón derecho sobre GlassFish y seleccionar "View Admin Console".
4. Crear nuevo Pool.
5. Crear el JDBC/JDNI.
6. En Netbeans hacer click derecho sobre Services > Databases > JDBC > New Connection.
   - Seleccionar el driver de MariaDB/MySQL.
   - Configurar la conexión con los datos de la base de datos creada (usuario, contraseña, URL).
   - Probar la conexión y finalizar.

## 3. Crear el Proyecto Maven

1. En Netbeans, ir a File > New Project.
2. Seleccionar "Maven" en Categories y "Web Application" en Projects.
3. Hacer click en Next.
4. Ingresar el Group Id (por ejemplo, com.miempresa) y Artifact Id (por ejemplo, inventario-backend).
5. Hacer click en Finish.

## 4. Endpoint de prueba

En lugar de usar Servlets, hay que usar JAX-RS para crear los endpoints RESTful. Aquí hay un ejemplo de cómo crear un endpoint simple que responda con "Hello, World!" cuando se accede a `/api/hello`.

1. Añadir al `pom.xml` las dependencias necesarias para JAX-RS (Jersey):

```xml
<dependencies>
    <!-- Jersey (JAX-RS) -->
    <dependency>
        <groupId>org.glassfish.jersey.core</groupId>
        <artifactId>jersey-server</artifactId>
        <version>3.1.2</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.containers</groupId>
        <artifactId>jersey-container-servlet</artifactId>
        <version>3.1.2</version>
    </dependency>
    <dependency>
        <groupId>jakarta.ws.rs</groupId>
        <artifactId>jakarta.ws.rs-api</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

2. Crear una clase de aplicación JAX-RS para definir la ruta base de la API:

```java
package com.inventariolia;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    // No necesitas agregar métodos aquí, JAX-RS detectará automáticamente los recursos
}
```

3. Crear una clase Java llamada `HelloResource` en el paquete `com.inventariolia.endpoints`.
4. Añadir el siguiente código a la clase:

```java
package com.inventariolia.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// Define la ruta del recurso, las anotaciones Path son anidables.
@Path("/hello")
public class HelloResource {

    // Endpoint que devuelve texto plano
    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloText() {
        return "Hello, World!";
    }

    // Endpoint que devuelve JSON
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHelloJson() {
        return "{\"message\": \"Hello, World!\"}";
    }
}
```

5. Probar el endpoint desplegando la aplicación en GlassFish y accediendo a:
   - `http://localhost:8080/InventarioLIA/api/hello/text` para obtener "Hello, World!" en texto plano.
   - `http://localhost:8080/InventarioLIA/api/hello/json` para obtener `{"message": "Hello, World!"}` en formato JSON.

## Añadir las Entity Classes

Botón derecho sobre el proyecto > New > Entity Classes from Database.

## Endpoint para devolver todos los elementos

Configurar el persistence.xml para poder usar la unidad de persistencia. Hay que indicar el Data source y darle un nombre a la PU: `InventarioLiaPU`.

Crear una clase Java llamada `ElementosResource` en el paquete `com.inventariolia.endpoints`.
Añadir el siguiente código a la clase:

```java
package com.inventariolia.endpoints;

import com.inventariolia.Elementos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/elementos")
public class ElementosResource {
    @PersistenceContext(unitName = "InventarioLiaPU")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Elementos> getAllElementos() {
        // Usar API Criteria para construir la consulta
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Elementos> cq = cb.createQuery(Elementos.class);
        Root<Elementos> root = cq.from(Elementos.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}
```

En este momento se puede presentar un problema de serialización cíclica debido a las relaciones entre las entidades. Para evitar esto, hay que modificar las entidades para que no se serialicen las relaciones que causan el ciclo.

En la entidad **Elementos** hay que añadir la anotación `@JsonbTransient`:

```java
import jakarta.json.bind.annotation.JsonbTransient;

@OneToMany(mappedBy = "elementos")
@JsonbTransient // Evita la serialización de la colección
private Collection<Movimientos> movimientosCollection;
```

En la entidad **Movimientos** hay que añadir la anotación `@JsonbTransient` en las relaciones para evitar problemas de serialización cíclica.

```java
import jakarta.json.bind.annotation.JsonbTransient;

@ManyToOne
@JoinColumns({
    @JoinColumn(name = "nro_lia", referencedColumnName = "nroLia"),
    @JoinColumn(name = "nro_unsj", referencedColumnName = "nroUnsj")
})
@JsonbTransient // Evita la serialización de la relación inversa
private Elementos elementos;
```

Y probar el endpoint accediendo a:
- `http://localhost:8080/InventarioLIA/api/elementos` para obtener todos los elementos en formato JSON.

## Configurando CORS

Hay dos formas de configurar CORS en GlassFish:
1. Usando el filtro CorsFilter de Tomcat (puede que GlassFish no incluya este filtro, y por lo tanto no encuentra el paquete).
2. Creando un filtro CORS personalizado.

### Opción 1: Usando el filtro CorsFilter de Tomcat

Añadir en el archivo `web.xml` del proyecto las siguientes líneas:

```xml
<filter>
    <filter-name>CorsFilter</filter-name>
    <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
    <init-param>
        <param-name>cors.allowed.origins</param-name>
        <param-value>*</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CorsFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

### Opción 2: Creando un filtro CORS personalizado

Crear una clase Java llamada `CorsFilter` en el paquete `com.inventariolia.filters` con el siguiente código:

```java
package com.inventariolia.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización si es necesaria
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Configurar los encabezados CORS
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");

        // Manejar solicitudes OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Limpieza si es necesaria
    }
}
```
Luego, añadir en el archivo `web.xml` del proyecto las siguientes líneas para registrar el filtro:

```xml
<filter>
    <filter-name>CorsFilter</filter-name>
    <filter-class>com.inventariolia.filters.CorsFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CorsFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
Con esto, el backend debería estar configurado para manejar solicitudes CORS correctamente.