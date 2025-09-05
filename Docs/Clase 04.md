Parte de la seguridad e Java, es que se trabaja sobre la maquina virtual de Java y no hay acceso directo al servidor físico donde se encuentra corriendo la maquina virtual de Java.

Cuando se quiere correr una aplicación web, se necesita el JDK y el IDE para el ecosistema Java, el JDK tiene una maquina virtual, donde se ejecuta la aplicación web.

## Applets y Servlets

Los Applets son componentes que se ejecutan del lado del cliente.
Los Servlets son componentes que se ejecutan del lado del servidor.

## GET vs POTS
En POST los datos son empaquetados, y en GET se envian por la URL.


## Proyecto HolaMundo
### Crear Proyecto

1. New Proyect > Java with Ant > Java web > Web Application
2. [](<assets/Pasted image 20250903171709.png>)
3. ![[Pasted image 20250903171753.png]]
4. Por ahora darle Finish, sin seleccionar Spring MVC o JSF.

### Modificar el Proyecto
1. Simplemente hay que crear un Servlet que va a genera la pagina con el formulario.

```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


/**
 *
 * @author martin
 */
@WebServlet(name = "HolaServlet", urlPatterns = {"/HolaServlet"})
public class HolaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>" + "<head><title>Hola</title></head>");
            out.println("<body bgcolor=\"#ffffff\">" + 
                "<img src=\"./assets/saludando.png\" alt=\"Duke no esta....\">" +
                "<h2>Hola, mi nombre is Luke. Cual es tu nombre?</h2>" +
                "<form method=\"get\">" +
                "<input type=\"text\" name=\"nombre\" size=\"25\">" +
                "<p></p>" + "<input type=\"submit\" value=\"Enviar\">" +
                "<input type=\"reset\" value=\"ReIniciar\">" +
                "</form>");
            String nombre = request.getParameter("nombre");
            if ((nombre != null) && (!nombre.isBlank())) {
                out.println("<h2> Holaaaa " + nombre + "</h2>");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respuesta");
                if (dispatcher != null) {
                    dispatcher.include(request, response);
                }
            }
            out.println("</body></html>");
            out.close();
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

En el ejercicio 1a, la idea es tener dos servlets, el primero muestra el formulario y el segundo simplemente devuelve una respuesta.
En el ejercicio 1b, la idea es que el primer servlet no redirija al segundo servlet, sino que lo llame para obtener una respuesta e incluirla en si mimo.

### Ejercicio 1a (dos servlets, uno redirige al otro)

- **Servlet 1** → genera el formulario (HTML con input de nombre).
    
- Cuando el usuario envía el formulario, la petición va directamente al **Servlet 2** (`/respuesta`).
    
- El **Servlet 2** recibe los datos y responde con el saludo.
    

👉 Acá no se usa `RequestDispatcher`. Cada servlet trabaja por separado: uno muestra, el otro responde.

---

### 📌 Ejercicio 1b (un servlet que incluye al otro)

- **Servlet 1** → genera el formulario **y procesa el parámetro `nombre`**.
    
- Si el usuario escribe un nombre, el **Servlet 1** no le da toda la responsabilidad al otro, sino que:
    
    1. Sigue generando su propio HTML.
        
    2. Usa **`RequestDispatcher.include(request, response)`** para **incrustar** la respuesta del **Servlet 2** dentro de su salida.
        

👉 Así, el usuario sigue interactuando con el **Servlet 1**, pero la respuesta se “complementa” con lo que produce el **Servlet 2**.


#### Pregunta 1: ¿Es una clase o una interfaz?
**`RequestDispatcher` es una interfaz** (está en el paquete `jakarta.servlet`).  
Esto significa que no se instancia directamente, sino que el contenedor (Tomcat, Jetty, etc.) devuelve una implementación cuando llamás a `getRequestDispatcher(...)`.

#### Pregunta 2: ¿A qué objeto se envía el mensaje `getServletContext()`?

El método `getServletContext()` se invoca sobre el **Servlet** (en realidad sobre `HttpServlet`, porque hereda de `GenericServlet`).

- Devuelve un objeto de tipo **`ServletContext`**, que representa el contexto de toda la aplicación web.
- Este contexto es compartido entre todos los servlets y recursos del mismo proyecto, y se usa para:
	- Obtener parámetros globales,
    - Compartir información entre servlets,
    - Acceder a recursos del servidor.

### Pregunta 3: Si respuesta es un servlet, ¿qué función tiene `getRequestDispatcher("/respuesta")`?

`getRequestDispatcher("/respuesta")` devuelve un objeto `RequestDispatcher` que apunta al recurso **`/respuesta`** (en este caso, un servlet con ese mapping).  
Con ese `RequestDispatcher` se puede:
- **forward(request, response)** → transferir la petición y la respuesta al servlet `/respuesta`. El servlet actual ya no genera salida.
- **include(request, response)** → incluir la salida generada por `/respuesta` dentro de la respuesta actual (el servlet principal sigue generando contenido).

En el ejemplo del práctico, se usa **`include`**, lo que significa que el contenido del servlet `/respuesta` se incrusta en el HTML generado por el primer servlet.

- **`include`** → el servlet principal **incluye** la salida de otro recurso dentro de su propia respuesta.
- **`forward`** → el servlet principal **transfiere la petición** al otro recurso y ya no produce salida propia.

El forward es lo que se utiliza en la arquitectura MVC, para que el primer servlet procese la informacion y delegue los datos de respuesta a otro servlet (o a un JSP) que se encarga de la presentacion.

## Ejercicio 2

Actualización en tiempo real (JavaScript – Ajax - Servlet) Crear un proyecto web que muestre la hora real actualizada cada segundo. Incluir un Servlet que devuelve la hora en formato JSON.

1. Crear el servlet HoraServlet:
```java
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HoraServlet", urlPatterns = {"/HoraServlet"})
public class HoraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        LocalTime hora = LocalTime.now();
        String json = String.format("{\"hora\":\"%s\"}", hora.toString());
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
    }
}
```

2. Añadir la Pagina con Ajax para llamar al Servlet que devuelve la hora:
```html
<!DOCTYPE html>
<html>
<head>
    <title>Hora Actual</title>
    <script>
        function actualizarHora() {
            fetch('HoraServlet')
                .then(response => response.json())
                .then(data => {
                    document.getElementById('hora').textContent = data.hora;
                });
        }
        setInterval(actualizarHora, 1000);
        window.onload = actualizarHora;
    </script>
</head>
<body>
    <h2>Hora actual:</h2>
    <div id="hora"></div>
</body>
</html>
```

## Ejercicio 3

Gestión de Usuarios:

Retomar el proyecto que gestiona cuenta de usuario y agregar un objeto de colección (puede ser una Lista) para almacenar la información correspondiente a varias cuentas de usuarios. Debe ser posible crear cuenta, iniciar sesión, cerrar sesión. Implementar protección de acceso a las páginas de gestión con filtros o verificaciones en servlet.

La persistencia de los datos enviados (por ejemplo, los usuarios guardados en una colección como ArrayList en el ServletContext) se mantiene mientras el servidor esté corriendo.
Cuando el servidor se detiene o reinicia, los datos en memoria se pierden y la colección se vacía.

1. Una clase para los usuarios, para poder tener una coleccion de usuarios:

```java
package Model;

public class Usuario {
    private String nombre;
    private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
```

2. Un servlet que se encarga de gestionar la coleccion de usuarios:

```java
package Servlets;

import Model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    @Override
    public void init() {
        // Inicializa la colección en el contexto si no existe
        if (getServletContext().getAttribute("usuarios") == null) {
            getServletContext().setAttribute("usuarios", new ArrayList<Usuario>());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        if (nombre != null && email != null && !nombre.isBlank() && !email.isBlank()) {
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) getServletContext().getAttribute("usuarios");
            usuarios.add(new Usuario(nombre, email));
        }
        response.sendRedirect("listaUsuarios.html");
    }
}
```

3. El formulario HTML para añadir usuarios.

```java
<!DOCTYPE html>
<html>
<head><title>Agregar Usuario</title></head>
<body>
    <form method="post" action="UsuarioServlet">
        Nombre: <input type="text" name="nombre"><br>
        Email: <input type="email" name="email"><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>
```

4. El servlet para mostrar la coleccion de usuarios

```java
package Servlets;

import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ListaUsuariosServlet", urlPatterns = {"/listaUsuarios.html"})
public class ListaUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) getServletContext().getAttribute("usuarios");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h2>Usuarios registrados:</h2><ul>");
            for (Usuario u : usuarios) {
                out.println("<li>" + u.getNombre() + " - " + u.getEmail() + "</li>");
            }
            out.println("</ul>");
            out.println("<a href='agregarUsuario.html'>Agregar otro usuario</a>");
        }
    }
}
```

## Uso de Ngrok

1. Instalar: 
```sh
curl -sSL https://ngrok-agent.s3.amazonaws.com/ngrok.asc \
  | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null \
  && echo "deb https://ngrok-agent.s3.amazonaws.com bookworm main" \
  | sudo tee /etc/apt/sources.list.d/ngrok.list \
  && sudo apt update \
  && sudo apt install ngrok
```
2. ngrok config add-authtoken <token>
3. Ejecutar: `ngrok http 8080`
