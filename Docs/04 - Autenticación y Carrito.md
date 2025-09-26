# Autenticación y Carrito

## Index.jsp

### Directiva page

```java
<%@ page contentType="text/html;charset=UTF-8" %>
```

Esta primer linea en todo documento JSP le indica al servidor como debe ser la respuesta. En este caso la respuesta es `text/html` y además que use la codificación UTF-8.

Esto no reemplaza las cabeceras meta del documento HTML, pues las cabeceras son para el browser, no para el servidor.

### Scriptlet para gestión de session

```java
<%
    if (session.getAttribute("usr") != null) {
        response.sendRedirect("productos.jsp"); // O la página que desees
        return;
    }
%>
```

Este primer bloque de codigo JSP (scriptet) se ejecuta cuando el coódigo es procesado por el servidor.

`session` es una instancia de `HttpSession`, que permite guardar datos asociados a un usuario mientras navega.

El Servlet que crea el objeto session y lo guarda sería el siguiente:

```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("usuario");
        String pass = request.getParameter("password");

        // Usuario y contraseña "fake"
        if ("asd".equals(user) && "1234".equals(pass)) {
            // Guardar usuario en sesión
            HttpSession session = request.getSession();
            session.setAttribute("usr", user);

            // Redirigir a info.jsp
            response.sendRedirect("info.jsp");
        } else {
            // Redirigir a index.jsp con error
            response.sendRedirect("index.jsp?error=1");
        }
    }
}
```

### JSESSIONID

Cuando se accede a un recurso de una aplicación Java Web, el servidor crea una sesión y le envia al cliente la cookie JSESSIONID.

Esta cookie es la que permite identificar al cliente en las siguientes peticiones, por eso para validar si un cliente esta logeado o no, es suficiente con preguntar si en el servidor `session.getAttribute("usr") != null`.

### HttpSession session

El objeto session permite guardar y recuperar claves en la sesion de cada usuario, del lado del servidor:

```java
// Guardar un valor
session.setAttribute("clave", valor);
// Recuperar un valor
session.getAttribute("clave");
```

### index.jsp completo

```java
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Verifica si el usuario ya está logeado
    if (session.getAttribute("usr") != null) {
        response.sendRedirect("info.jsp");
        return;
    }
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Iniciar sesión</h2>
    <form action="AuthServlet" method="post">
        Usuario: <input type="text" name="usuario" required><br>
        Contraseña: <input type="password" name="password" required><br>
        <input type="submit" value="Ingresar">
    </form>
    <% String error = request.getParameter("error");
       if (error != null) { %>
        <p style="color:red;">Usuario o contraseña incorrectos</p>
    <% } %>
</body>
</html>
```

### request.getParameter()

> [!INFO]
> En los JSP, los objetos request, response, session, out, application, config y pageContext estan disponibles automaticamente, es decir, no necesitan ser importados como en los Servlets.

Cuando se solicita un JSP, el objeto `request` contiene todos los parametros pasados por get (y se accede a ellos por medio de `request.getParameter("nombreDelParametro")`).

## AuthServlet

```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package manejador;

import bd.Dato;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Autorizar", urlPatterns = {"/Autorizar"})
public class Autorizar extends HttpServlet {
    private Dato ds = Dato.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if("registro".equals(action)){
            String name = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Usuario u = new Usuario(name,email,password);
            boolean ok = ds.addUser(u);
            if(ok){
                request.getSession().setAttribute("usr", u);
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.getWriter().write("Usuario ya existe");
            }
        } else if("login".equals(action)){
            String email = request.getParameter("usuario");
            String password = request.getParameter("password");
            System.out.println("[DEBUG] Email recibido: " + email);
            System.out.println("[DEBUG] Password recibido: " + password);
            Usuario u = ds.findUser(email);
            
            if(u != null){
                System.out.println("[DEBUG] Usuario encontrado: " + u.getEmail());
                System.out.println("[DEBUG] Clave almacenada: " + u.getClave());
            } else {
                System.out.println("[DEBUG] Usuario no encontrado en Dato");
                for (Usuario ua : ds.getAllUsers()) {
                    System.out.println("Usuario: " + ua.getNombre() +
                                       " - Email: " + ua.getEmail() +
                                       " - Pass: " + ua.getClave());
                }
            }
            
            if(u != null && u.getClave().equals(password)){
                request.getSession().setAttribute("usr", u);
                response.sendRedirect(request.getContextPath() + "/carrito.jsp");
            } else {
                response.getWriter().write("Credenciales invalidas");
            }
        } else if("logout".equals(action)){
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(400);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
```

El AuthServlet (o cualquier servlet en Java) es una clase que extiende HttpServlet y sirve para manejar peticiones HTTP (como login, registro, etc.).

Según el tipo de petición realizada (el verbo usado), los HttpServlets cuentan con los siguientes metodos:
- **doGet:** para peticiones HTTP GET
- **doPost:** para peticiones HTTP POST
- **doPut:** para peticiones HTTP PUT
- **doDelete:** para peticiones HTTP DELETE
- **doOptions:** para peticiones HTTP OPTIONS
- **doHead:** para peticiones HTTP HEAD
- **doTrace:** para peticiones HTTP TRACE

El servlet por defecto es:

```java
@WebServlet("/MiServlet")
public class MiServlet extends HttpServlet {
    // No sobrescribe ningún método
}
```

Este servlet al no sobreescribir los metodos doGet y doPost, responde con un error HTTP 405 (método no permitido).

El servlet con los metodos sobrescritos sería:

```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/MiServlet")
public class MiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para peticiones GET
        response.getWriter().println("Respuesta a GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para peticiones POST
        response.getWriter().println("Respuesta a POST");
    }
}
```

## Metodo para evaluar las peticiones de Login, Logout y Register

El formulario que haga la solicitud debe incluir una input oculto donde se indique que acción se pretende.

```java
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if("registro".equals(action)){
            String name = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Usuario u = new Usuario(name,email,password);
            boolean ok = ds.addUser(u);
            if(ok){
                request.getSession().setAttribute("usr", u);
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.getWriter().write("Usuario ya existe");
            }
        } else if("login".equals(action)){
            String email = request.getParameter("usuario");
            String password = request.getParameter("password");
            System.out.println("[DEBUG] Email recibido: " + email);
            System.out.println("[DEBUG] Password recibido: " + password);
            Usuario u = ds.findUser(email);
            
            if(u != null){
                System.out.println("[DEBUG] Usuario encontrado: " + u.getEmail());
                System.out.println("[DEBUG] Clave almacenada: " + u.getClave());
            } else {
                System.out.println("[DEBUG] Usuario no encontrado en Dato");
                for (Usuario ua : ds.getAllUsers()) {
                    System.out.println("Usuario: " + ua.getNombre() +
                                       " - Email: " + ua.getEmail() +
                                       " - Pass: " + ua.getClave());
                }
            }
            
            if(u != null && u.getClave().equals(password)){
                request.getSession().setAttribute("usr", u);
                response.sendRedirect(request.getContextPath() + "/carrito.jsp");
            } else {
                response.getWriter().write("Credenciales invalidas");
            }
        } else if("logout".equals(action)){
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(400);
        }
    }
```

Aunque en este ejemplo, el response parece actuar como un return, no es asi, el response por medio de sus metodos prepara la respuestasolo va preparando la respuesta.

Solo cuando terminan de ejecutarse los métodos doGet o doPost es que se envia la respuesta.

- getWriter(): Añade texto al cuerpo de la respuesta.
- sendError(): Reemplaza cualquier respuesta por una respuesta de error predefinida e impide seguir modificando la respuesta. Cualquier intento de modificar la respuesta tras un sendError() sera ignorado o lanzara una excepcion en el servidor.
- sendRedirect(): Provoca una redireccion del cliente a otra URL.
- setStatus(): define el código de status en la respuesta.
- setContentType(): Define el tipo de contenido de la respuesta.
- setHeader(): Añade o modifica una cabecera HTTP de la respuesta.
- getOutputStream(): Permite escribir datos binarios en la respuesta, para poder enviar imagenes o archivos.

## Paginas de Error

Cuando se utiliza `sendError()`, en `web.xml` se puede configurar que JSP se debe utilizar para que el cliente reciba el error.

```xml
<error-page>
    <error-code>400</error-code>
    <location>/error400.jsp</location>
</error-page>
<error-page>
    <error-code>404</error-code>
    <location>/error404.jsp</location>
</error-page>
<error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/errorGeneral.jsp</location>
</error-page>
```