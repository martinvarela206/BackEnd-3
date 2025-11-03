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