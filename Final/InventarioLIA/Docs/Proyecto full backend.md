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
    nro_lia VARCHAR(25) PRIMARY KEY,
    nro_unsj VARCHAR(25),
    tipo ENUM('cpu', 'monitor', 'switch', 'router', 'impresora', 'teclado', 'mouse', 'proyector', 'otro') NOT NULL,
    descripcion VARCHAR(255),
    cantidad INT
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
    CONSTRAINT fk_elementos FOREIGN KEY (nro_lia) REFERENCES elementos(nro_lia) ON DELETE CASCADE,
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
('UNSJ003', 'UNSJ003', 'mouse', 'Mouse inalámbrico Logitech M185', 15),
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
('UNSJ003', 'UNSJ003', 4, 'ingresado', 'Lab Hardware', '2025-11-09 11:30:00', 'Ingreso inicial'),
('LIA004', 'UNSJ004', 1, 'ingresado', 'Oficina A', '2025-11-09 12:00:00', 'Ingreso inicial');

-- Mantener los movimientos adicionales para variedad de estados
INSERT INTO movimientos (nro_lia, nro_unsj, user_id, estado, ubicacion, fecha, comentario) VALUES
('LIA002', 'UNSJ002', 2, 'guardado', 'Lab PM', '2025-11-09 11:00:00', 'Guardado en el laboratorio'),
('LIA003', NULL, 3, 'funcionando', 'Lab Redes', '2025-11-09 12:00:00', 'En uso en el laboratorio de redes'),
('UNSJ003', 'UNSJ003', 4, 'prestado', 'Prestado', '2025-11-09 13:00:00', 'Prestado al Departamento de Informática'),
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

## 3. Crear el Proyecto Maven Web

1. En Netbeans, ir a File > New Project.
2. Seleccionar "Maven" en Categories y "Web Application" en Projects.
3. Hacer click en Next.
4. Ingresar el Group Id: com.martindev y el Artifact Id: inventariolia.
5. Hacer click en Finish.

## 4. Configurar el `pom.xml`

Hay que añadir la dependencia del conector MySQL para MariaDB:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>2.0.0</version>
</dependency>
```

## 5. Hay que configurar el `persistence.xml`

Hay que abrir el archivo en Files > `src/main/resources/META-INF/persistence.xml` y configurarlo de la siguiente manera:

- PU: InventarioLiaPU
- Data Source: jdbc/inventariolia

## 6. Generar las Entity Classes desde la base de datos

> [!WARNING]
> GlassFish debe estar corriendo y la conexión a la base de datos debe estar configurada correctamente en Netbeans.

Botón derecho sobre el proyecto > New > Entity Classes from Database. Seleccionar todas las tablas.

## 7. Crear los EJB Facade

Botón derecho sobre el proyecto > New > Session Beans for Entity Classes. Selecciona todas las entidades y genera los EJB Facade correspondientes.


## 8. Crear Servlets y JSPs para mostrar los datos

En vez de acceder a la lógica de negocio directamente desde los JSPs, se recomienda utilizar el patrón MVC: los Servlets actúan como controladores, obtienen los datos y los envían a los JSPs para su visualización.

### Modificar index.html para acceder al listado de elementos

```html
<a href="elementos">Ver Lista de Elementos</a>
```

### Pasos para crear los Servlets

1. Crea un Servlet para cada funcionalidad principal (por ejemplo, `ElementosServlet` y `MovimientosServlet`).
2. En cada Servlet, utiliza el EntityManager o el EJB Facade para obtener los datos.
3. Guarda los datos como atributos en el request.
4. Redirige (forward) la petición al JSP correspondiente para mostrar los datos.

### Ejemplo: Servlet para mostrar todos los elementos

1. Crea la clase `ElementosServlet` en el paquete `com.martindev.inventariolia.servlets`:

```java
package com.martindev.inventariolia.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;

@WebServlet("/elementos")
public class ElementosServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Elementos> lista = elementosFacade.findAll();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("elementos.jsp").forward(request, response);
    }
}
```

2. El JSP `elementos.jsp` solo debe encargarse de mostrar los datos:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Lista de Elementos</h1>
<table border="1">
    <tr>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Tipo</th>
        <th>Descripción</th>
        <th>Cantidad</th>
    </tr>
    <c:forEach var="e" items="${lista}">
        <tr>
            <td>${e.nroLia}</td>
            <td>${e.nroUnsj}</td>
            <td>${e.tipo}</td>
            <td>${e.descripcion}</td>
            <td>${e.cantidad}</td>
        </tr>
    </c:forEach>
</table>
```

### Ejemplo: Servlet para mostrar movimientos de un elemento

1. Crea la clase `MovimientosServlet` en el paquete `com.martindev.inventariolia.controllers`:

```java
package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;

@WebServlet("/elementos")
public class ElementosServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Elementos> lista = elementosFacade.findAll();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("elementostest.jsp").forward(request, response);
    }
}
```

2. El JSP `movimientos.jsp` solo debe mostrar los movimientos:

```jsp
<%@ page import="com.martindev.inventariolia.Elementos" %>
<%@ page import="java.util.List" %>
<%
    List<Elementos> lista = (List<Elementos>) request.getAttribute("lista");
%>
<h1>Lista de Elementos</h1>
<table border="1">
    <tr>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Tipo</th>
        <th>Descripción</th>
        <th>Cantidad</th>
    </tr>
    <% for(Elementos e : lista) { %>
    <tr>
        <td><%= e.getNroLia() %></td>
        <td><%= e.getNroUnsj() %></td>
        <td><%= e.getTipo() %></td>
        <td><%= e.getDescripcion() %></td>
        <td><%= e.getCantidad() %></td>
    </tr>
    <% } %>
</table>
```

### Desplegar y probar

1. Compila y despliega el proyecto en GlassFish.
2. Accede a `http://localhost:8080/InventarioLIA/elementos.jsp` para ver la lista de elementos.

## 9. Añadir, Modificar y Eliminar Elementos

Ahora vamos a completar el CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad Elementos.

### 9.1. Añadir botón "Añadir" y columna "Acciones" en el listado

Modifica el JSP de listado de elementos (`elementos.jsp`) para agregar:
- Un botón "Añadir" que lleva a un formulario para crear un nuevo elemento.
- Una columna "Acciones" con enlaces para modificar y eliminar cada elemento.

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Lista de Elementos</h1>
<a href="nuevoelemento.jsp">Añadir Elemento</a>
<table border="1">
    <tr>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Tipo</th>
        <th>Descripción</th>
        <th>Cantidad</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="e" items="${lista}">
        <tr>
            <td>${e.nroLia}</td>
            <td>${e.nroUnsj}</td>
            <td>${e.tipo}</td>
            <td>${e.descripcion}</td>
            <td>${e.cantidad}</td>
            <td>
                <a href="editarelemento.jsp?nroLia=${e.nroLia}">Modificar</a> |
                <a href="elementos?accion=eliminar&nroLia=${e.nroLia}" onclick="return confirm('¿Seguro que desea eliminar este elemento?');">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>
```

### 9.2. Formulario para añadir un elemento (`nuevoelemento.jsp`)

```jsp
<form action="elementos" method="post">
    <input type="hidden" name="accion" value="crear" />
    <label>Nro LIA: <input type="text" name="nroLia" required></label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj"></label><br>
    <label>Tipo:
        <select name="tipo" required>
            <option value="cpu">CPU</option>
            <option value="monitor">Monitor</option>
            <option value="switch">Switch</option>
            <option value="router">Router</option>
            <option value="impresora">Impresora</option>
            <option value="teclado">Teclado</option>
            <option value="mouse">Mouse</option>
            <option value="proyector">Proyector</option>
            <option value="otro">Otro</option>
        </select>
    </label><br>
    <label>Descripción: <input type="text" name="descripcion"></label><br>
    <label>Cantidad: <input type="number" name="cantidad" min="1" required></label><br>
    <button type="submit">Guardar</button>
    <a href="elementos">Cancelar</a>
</form>
```

### 9.3. Formulario para modificar un elemento (`editarelemento.jsp`)

```jsp
<form action="elementos" method="post">
    <input type="hidden" name="accion" value="modificar" />
    <input type="hidden" name="nroLia" value="${elemento.nroLia}" />
    <label>Nro LIA: <b>${elemento.nroLia}</b></label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj" value="${elemento.nroUnsj}"></label><br>
    <label>Tipo:
        <select name="tipo">
            <option value="cpu" ${elemento.tipo == 'cpu' ? 'selected' : ''}>CPU</option>
            <option value="monitor" ${elemento.tipo == 'monitor' ? 'selected' : ''}>Monitor</option>
            <option value="switch" ${elemento.tipo == 'switch' ? 'selected' : ''}>Switch</option>
            <option value="router" ${elemento.tipo == 'router' ? 'selected' : ''}>Router</option>
            <option value="impresora" ${elemento.tipo == 'impresora' ? 'selected' : ''}>Impresora</option>
            <option value="teclado" ${elemento.tipo == 'teclado' ? 'selected' : ''}>Teclado</option>
            <option value="mouse" ${elemento.tipo == 'mouse' ? 'selected' : ''}>Mouse</option>
            <option value="proyector" ${elemento.tipo == 'proyector' ? 'selected' : ''}>Proyector</option>
            <option value="otro" ${elemento.tipo == 'otro' ? 'selected' : ''}>Otro</option>
        </select>
    </label><br>
    <label>Descripción: <input type="text" name="descripcion" value="${elemento.descripcion}"></label><br>
    <label>Cantidad: <input type="number" name="cantidad" min="1" value="${elemento.cantidad}" required></label><br>
    <button type="submit">Guardar</button>
    <a href="elementos">Cancelar</a>
</form>
```

### 9.4. Modificar el Servlet `ElementosServlet` para manejar el CRUD

> **Nota:** Debes modificar el servlet para manejar las acciones de crear, modificar y eliminar. Aquí tienes un ejemplo completo:

```java
package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;

@WebServlet("/elementos")
public class ElementosServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("editar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos elemento = elementosFacade.find(nroLia);
            request.setAttribute("elemento", elemento);
            request.getRequestDispatcher("editarelemento.jsp").forward(request, response);
        } else {
            List<Elementos> lista = elementosFacade.findAll();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("elementos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("crear".equals(accion)) {
            Elementos e = new Elementos();
            e.setNroLia(request.getParameter("nroLia"));
            e.setNroUnsj(request.getParameter("nroUnsj"));
            e.setTipo(request.getParameter("tipo"));
            e.setDescripcion(request.getParameter("descripcion"));
            e.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            elementosFacade.create(e);
            response.sendRedirect("elementos");
        } else if ("modificar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos e = elementosFacade.find(nroLia);
            if (e != null) {
                e.setNroUnsj(request.getParameter("nroUnsj"));
                e.setTipo(request.getParameter("tipo"));
                e.setDescripcion(request.getParameter("descripcion"));
                e.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                elementosFacade.edit(e);
            }
            response.sendRedirect("elementos");
        } else if ("eliminar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos e = elementosFacade.find(nroLia);
            if (e != null) {
                elementosFacade.remove(e);
            }
            response.sendRedirect("elementos");
        }
    }
}
```

---

> **Notas:**
> - Asegúrate de que los JSP `elementos.jsp`, `nuevoelemento.jsp` y `editarelemento.jsp` estén en `src/main/webapp/`.
> - Si ya tienes un archivo `ElementosServlet.java`, reemplázalo por el código completo anterior para soportar el CRUD.
> - Si usas validaciones adicionales o campos distintos, ajusta los formularios y el servlet según tus necesidades.

## 10. Movimiento inicial por defecto


Cuando un nuevo elemento es creado, se debe insertar automáticamente un movimiento inicial de 'ingresado'. Esto se puede hacer en el método `doPost` del servlet `ElementosServlet`, justo después de crear el nuevo elemento.

### ¿Cómo hacerlo?

1. **Asegúrate de tener el EJB Facade para la entidad `Movimientos`** (por ejemplo, `MovimientosFacade`).
2. **Inyecta el facade en tu servlet:**

```java
@EJB
private MovimientosFacade movimientosFacade;
```

3. **Después de crear el elemento, crea e inserta el movimiento inicial:**

```java
if ("crear".equals(accion)) {
    Elementos e = new Elementos();
    e.setNroLia(request.getParameter("nroLia"));
    e.setNroUnsj(request.getParameter("nroUnsj"));
    e.setTipo(request.getParameter("tipo"));
    e.setDescripcion(request.getParameter("descripcion"));
    e.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
    elementosFacade.create(e);

    // Crear movimiento inicial
    Movimientos m = new Movimientos();
    m.setNroLia(e.getNroLia());
    m.setNroUnsj(e.getNroUnsj());
    m.setEstado("ingresado");
    m.setUbicacion("Lab FB"); // Puedes ajustar la ubicación por defecto
    m.setFecha(new java.util.Date());
    m.setComentario("Ingreso inicial");
    m.setUserId(1); // ID del usuario que realiza la acción (ajusta según tu lógica de sesión)
    movimientosFacade.create(m);

    response.sendRedirect("elementos");
}
```

> **Notas:**
> - Asegúrate de importar la clase `Movimientos` y de tener el método `create` en el facade.
> - El campo `userId` debe corresponder al usuario logueado. Si tienes autenticación, obtén el ID del usuario de la sesión.
> - Puedes ajustar la ubicación y el comentario según tu lógica de negocio.

Con esto, cada vez que se cree un nuevo elemento, automáticamente se insertará un movimiento inicial de tipo 'ingresado'.

## 11. Mostrar detalles de un elemento y Listar sus movimientos


Al hacer click sobre la descripción de un elemento en el listado, se debe redirigir a una página de detalles que muestre toda la información del elemento y una tabla con todos sus movimientos asociados.

### 11.1. Agregar el enlace en el listado

En `elementos.jsp`, haz que la descripción sea un enlace:

```jsp
<td><a href="detalleelemento?nroLia=${e.nroLia}">${e.descripcion}</a></td>
```

### 11.2. Crear el Servlet para mostrar detalles

Crea un servlet `DetalleElementoServlet`:

```java
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.MovimientosFacade;

@WebServlet("/detalleelemento")
public class DetalleElementoServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nroLia = request.getParameter("nroLia");
        Elementos elemento = elementosFacade.find(nroLia);
        List<Movimientos> movimientos = movimientosFacade.findByNroLia(elemento);
        request.setAttribute("elemento", elemento);
        request.setAttribute("movimientos", movimientos);
        request.getRequestDispatcher("detalleelemento.jsp").forward(request, response);
    }
}
```

> **Nota:** Debes implementar el método `findByNroLia(Elementos e)` en tu `MovimientosFacade`:

```java
import java.util.List;

public List<Movimientos> findByNroLia(Elementos elemento) {
    return em.createQuery("SELECT m FROM Movimientos m WHERE m.nroLia = :elemento", Movimientos.class)
             .setParameter("elemento", elemento)
             .getResultList();
}
```

### 11.3. Crear el JSP de detalle (`detalleelemento.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Detalle del Elemento</h2>
<table border="1">
    <tr><th>Nro LIA</th><td>${elemento.nroLia}</td></tr>
    <tr><th>Nro UNSJ</th><td>${elemento.nroUnsj}</td></tr>
    <tr><th>Tipo</th><td>${elemento.tipo}</td></tr>
    <tr><th>Descripción</th><td>${elemento.descripcion}</td></tr>
    <tr><th>Cantidad</th><td>${elemento.cantidad}</td></tr>
</table>

<h3>Movimientos asociados</h3>
<table border="1">
    <tr>
        <th>Fecha</th>
        <th>Estado</th>
        <th>Ubicación</th>
        <th>Comentario</th>
    </tr>
    <c:forEach var="m" items="${movimientos}">
        <tr>
            <td>${m.fecha}</td>
            <td>${m.estado}</td>
            <td>${m.ubicacion}</td>
            <td>${m.comentario}</td>
        </tr>
    </c:forEach>
</table>
<a href="elementos">Volver al listado</a>
```

---

Con esto, al hacer click en la descripción de un elemento, verás todos sus datos y los movimientos asociados.

## 12. TODO

## 12. CRUD completo para Movimientos (Añadir, Modificar, Eliminar)

Vamos a implementar el CRUD completo para la entidad **Movimientos**, mostrando el nombre del usuario que hizo cada movimiento.

### Archivos creados o modificados

- `src/main/java/com/martindev/inventariolia/controllers/MovimientosServlet.java` (nuevo)
- `src/main/webapp/movimientos.jsp` (nuevo)
- `src/main/webapp/nuevomovimiento.jsp` (nuevo)
- `src/main/webapp/editarmovimiento.jsp` (nuevo)

### 12.1. Servlet MovimientosServlet

```java
package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

@WebServlet("/movimientos")
public class MovimientosServlet extends HttpServlet {
    @EJB
    private MovimientosFacade movimientosFacade;
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private UsuariosFacade usuariosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("nuevo".equals(accion)) {
            List<Elementos> elementos = elementosFacade.findAll();
            List<Usuarios> usuarios = usuariosFacade.findAll();
            request.setAttribute("elementos", elementos);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("nuevomovimiento.jsp").forward(request, response);
        } else if ("editar".equals(accion)) {
            String id = request.getParameter("id");
            Movimientos movimiento = movimientosFacade.find(Integer.valueOf(id));
            List<Elementos> elementos = elementosFacade.findAll();
            List<Usuarios> usuarios = usuariosFacade.findAll();
            request.setAttribute("movimiento", movimiento);
            request.setAttribute("elementos", elementos);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("editarmovimiento.jsp").forward(request, response);
        } else {
            List<Movimientos> lista = movimientosFacade.findAll();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("movimientos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("crear".equals(accion)) {
            Movimientos m = new Movimientos();
            String nroLiaParam = request.getParameter("nroLia");
            Elementos elemento = elementosFacade.find(nroLiaParam);
            m.setNroLia(elemento);
            m.setNroUnsj(request.getParameter("nroUnsj"));
            m.setEstado(request.getParameter("estado"));
            m.setUbicacion(request.getParameter("ubicacion"));
            m.setComentario(request.getParameter("comentario"));
            m.setFecha(new java.util.Date());
            int userId = Integer.parseInt(request.getParameter("userId"));
            Usuarios usuario = usuariosFacade.find(userId);
            m.setUserId(usuario);
            movimientosFacade.create(m);
            response.sendRedirect("movimientos");
        } else if ("modificar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Movimientos m = movimientosFacade.find(id);
            if (m != null) {
                String nroLiaParam = request.getParameter("nroLia");
                Elementos elemento = elementosFacade.find(nroLiaParam);
                m.setNroLia(elemento);
                m.setNroUnsj(request.getParameter("nroUnsj"));
                m.setEstado(request.getParameter("estado"));
                m.setUbicacion(request.getParameter("ubicacion"));
                m.setComentario(request.getParameter("comentario"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                Usuarios usuario = usuariosFacade.find(userId);
                m.setUserId(usuario);
                movimientosFacade.edit(m);
            }
            response.sendRedirect("movimientos");
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Movimientos m = movimientosFacade.find(id);
            if (m != null) {
                movimientosFacade.remove(m);
            }
            response.sendRedirect("movimientos");
        }
    }
}
```

### 12.2. JSP para listar movimientos (`movimientos.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Lista de Movimientos</h1>
<a href="movimientos?accion=nuevo">Añadir Movimiento</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Estado</th>
        <th>Ubicación</th>
        <th>Fecha</th>
        <th>Comentario</th>
        <th>Usuario</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="m" items="${lista}">
        <tr>
            <td>${m.id}</td>
            <td>${m.nroLia.nroLia}</td>
            <td>${m.nroUnsj}</td>
            <td>${m.estado}</td>
            <td>${m.ubicacion}</td>
            <td>${m.fecha}</td>
            <td>${m.comentario}</td>
            <td>${m.userId.nombre}</td>
            <td>
                <a href="movimientos?accion=editar&id=${m.id}">Modificar</a> |
                <a href="movimientos?accion=eliminar&id=${m.id}" onclick="return confirm('¿Seguro que desea eliminar este movimiento?');">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="elementos">Volver a elementos</a>
```

### 12.3. JSP para crear movimiento (`nuevomovimiento.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Nuevo Movimiento</h2>
<form action="movimientos" method="post">
    <input type="hidden" name="accion" value="crear" />
    <label>Nro LIA:
        <select name="nroLia" required>
            <c:forEach var="e" items="${elementos}">
                <option value="${e.nroLia}">${e.nroLia}</option>
            </c:forEach>
        </select>
    </label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj"></label><br>
    <label>Estado:
        <select name="estado" required>
            <option value="ingresado">Ingresado</option>
            <option value="guardado">Guardado</option>
            <option value="funcionando">Funcionando</option>
            <option value="dado de baja">Dado de baja</option>
            <option value="prestado">Prestado</option>
        </select>
    </label><br>
    <label>Ubicación: <input type="text" name="ubicacion" required></label><br>
    <label>Comentario: <input type="text" name="comentario"></label><br>
    <label>Usuario:
        <select name="userId" required>
            <c:forEach var="u" items="${usuarios}">
                <option value="${u.id}">${u.nombre}</option>
            </c:forEach>
        </select>
    </label><br>
    <button type="submit">Guardar</button>
    <a href="movimientos">Cancelar</a>
</form>
```

### 12.4. JSP para editar movimiento (`editarmovimiento.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Editar Movimiento</h2>
<form action="movimientos" method="post">
    <input type="hidden" name="accion" value="modificar" />
    <input type="hidden" name="id" value="${movimiento.id}" />
    <label>Nro LIA:
        <select name="nroLia" required>
            <c:forEach var="e" items="${elementos}">
                <option value="${e.nroLia}" ${movimiento.nroLia.nroLia == e.nroLia ? 'selected' : ''}>${e.nroLia}</option>
            </c:forEach>
        </select>
    </label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj" value="${movimiento.nroUnsj}"></label><br>
    <label>Estado:
        <select name="estado" required>
            <option value="ingresado" ${movimiento.estado == 'ingresado' ? 'selected' : ''}>Ingresado</option>
            <option value="guardado" ${movimiento.estado == 'guardado' ? 'selected' : ''}>Guardado</option>
            <option value="funcionando" ${movimiento.estado == 'funcionando' ? 'selected' : ''}>Funcionando</option>
            <option value="dado de baja" ${movimiento.estado == 'dado de baja' ? 'selected' : ''}>Dado de baja</option>
            <option value="prestado" ${movimiento.estado == 'prestado' ? 'selected' : ''}>Prestado</option>
        </select>
    </label><br>
    <label>Ubicación: <input type="text" name="ubicacion" value="${movimiento.ubicacion}" required></label><br>
    <label>Comentario: <input type="text" name="comentario" value="${movimiento.comentario}"></label><br>
    <label>Usuario:
        <select name="userId" required>
            <c:forEach var="u" items="${usuarios}">
                <option value="${u.id}" ${movimiento.userId.id == u.id ? 'selected' : ''}>${u.nombre}</option>
            </c:forEach>
        </select>
    </label><br>
    <button type="submit">Guardar</button>
    <a href="movimientos">Cancelar</a>
</form>
```

### 13.5 Añadir boton "Nuevo Movimiento" en detalle del elemento

En `detalleelemento.jsp`, añade un botón para crear un nuevo movimiento asociado al elemento actual:

```jsp
<a href="movimientos?accion=nuevo&nroLia=${elemento.nroLia}">Añadir Nuevo Movimiento</a>
```

## 13. Implementacion de la autenticacion y autorizacion por roles de usuario

## 13. Implementación de autenticación y autorización por roles

En esta sección se explica cómo implementar un sistema de login y control de acceso por roles, permitiendo que:

- Los usuarios pueden tener más de un rol.
- El coordinador accede a todos los CRUDs.
- El técnico puede añadir elementos y movimientos, pero no puede editar ni eliminar.
- El revisor solo puede ver los listados.
- A futuro, el administrador podrá gestionar usuarios.

### 13.1. Estructura general

1. **Login:** Formulario JSP y un servlet que valida usuario y contraseña.
2. **Guardar usuario y roles en sesión:** Tras login exitoso, guardar el usuario y sus roles en `HttpSession`.
3. **Filtro de autorización:** Un filtro (`Filter`) que verifica si el usuario está autenticado y tiene el rol necesario para cada URL.
4. **Logout:** Servlet para cerrar sesión.
5. **Mostrar alertas:** Usar un atributo de sesión para mostrar mensajes como "No tiene permisos necesarios".

---

### 13.2. Formulario de login (`login.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Iniciar sesión</h2>
<form action="login" method="post">
    <label>Usuario: <input type="text" name="nombre" required></label><br>
    <label>Contraseña: <input type="password" name="password" required></label><br>
    <button type="submit">Ingresar</button>
</form>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
```

---

### 13.3. Servlet de login (`LoginServlet.java`)

Ubicación sugerida: `com.martindev.inventariolia.controllers.LoginServlet`

```java
package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;
import com.martindev.inventariolia.Roles;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @EJB
    private UsuariosFacade usuariosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        Usuarios usuario = usuariosFacade.findByNombreYPassword(nombre, password);
        if (usuario != null) {
            List<Roles> roles = usuariosFacade.obtenerRoles(usuario);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("roles", roles);
            response.sendRedirect("inicio.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
```

**Notas:**
- Debes implementar en `UsuariosFacade` el método `findByNombreYPassword(String nombre, String password)` y `obtenerRoles(Usuarios usuario)` que devuelvan el usuario y sus roles.

---

### 13.4. Filtro de autorización (`AuthFilter.java`)

Ubicación sugerida: `com.martindev.inventariolia.filters.AuthFilter`

```java
package com.martindev.inventariolia.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.martindev.inventariolia.Roles;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Permitir acceso libre a login y recursos estáticos
        if (uri.endsWith("login") || uri.endsWith("login.jsp") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")) {
            chain.doFilter(req, res);
            return;
        }

        // Verificar autenticación
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Verificar autorización por roles
        @SuppressWarnings("unchecked")
        List<Roles> roles = (List<Roles>) session.getAttribute("roles");
        boolean autorizado = false;

        // Lógica de autorización por URL y rol
        if (uri.contains("elementos") || uri.contains("movimientos")) {
            if (tieneRol(roles, "user_admin") || tieneRol(roles, "coordinador")) {
                autorizado = true; // CRUD completo
            } else if (tieneRol(roles, "tecnico")) {
                // Solo permitir añadir (POST con accion=crear)
                String accion = request.getParameter("accion");
                if ("crear".equals(accion) && "POST".equals(request.getMethod())) {
                    autorizado = true;
                } else if ("GET".equals(request.getMethod())) {
                    autorizado = true; // Puede ver listados
                }
            } else if (tieneRol(roles, "revisor")) {
                if ("GET".equals(request.getMethod())) {
                    autorizado = true; // Solo ver listados
                }
            }
        } else {
            autorizado = true; // Otras páginas públicas
        }

        if (!autorizado) {
            session.setAttribute("alerta", "No tiene permisos necesarios");
            response.sendRedirect("inicio.jsp");
            return;
        }

        chain.doFilter(req, res);
    }

    private boolean tieneRol(List<Roles> roles, String rol) {
        for (Roles r : roles) {
            if (r.getRol().equals(rol)) return true;
        }
        return false;
    }
}
```

---

### 13.5. Mostrar alertas en la página de inicio (`inicio.jsp`)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.alerta}">
    <div style="color:red;">${sessionScope.alerta}</div>
    <c:remove var="alerta" scope="session" />
</c:if>
```

---

### 13.6. Logout (`LogoutServlet.java`)

```java
package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
```

---

### 13.7. Métodos sugeridos para `UsuariosFacade`

```java
// En UsuariosFacade.java
public Usuarios findByNombreYPassword(String nombre, String password) {
    try {
        return em.createQuery("SELECT u FROM Usuarios u WHERE u.nombre = :nombre AND u.password = :password", Usuarios.class)
            .setParameter("nombre", nombre)
            .setParameter("password", password)
            .getSingleResult();
    } catch (Exception e) {
        return null;
    }
}

public List<Roles> obtenerRoles(Usuarios usuario) {
    return em.createQuery("SELECT r FROM Roles r JOIN r.usuariosList u WHERE u.id = :userId", Roles.class)
        .setParameter("userId", usuario.getId())
        .getResultList();
}
```

---

Con esto tienes un sistema de autenticación y autorización por roles, extensible para futuros permisos y CRUD de usuarios.

