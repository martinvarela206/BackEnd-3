# Clase 04: Componentes Web - Servlets

Bienvenidos a la cuarta clase de Backend. Hoy aprenderemos qué son los Servlets, cómo funcionan y por qué son fundamentales para el desarrollo de aplicaciones web en Java EE. Cada concepto se explica de forma sencilla para facilitar el aprendizaje.

---

## 1. ¿Qué son los Servlets?

Un Servlet es un objeto Java que extiende las capacidades de un servidor web, permitiendo crear contenido dinámico (por ejemplo, páginas personalizadas para cada usuario). Los Servlets funcionan bajo el modelo de programación de requerimiento-respuesta.

**Explicación:**
Cuando un usuario hace una petición (por ejemplo, al ingresar una URL o enviar un formulario), el Servlet recibe esa petición, la procesa y genera una respuesta (como una página HTML).

---

## 2. Ejemplo de Servlet

```java
@WebServlet(urlPatterns = {"/MiServlet"})
public class MiServlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<title>Hello World!</title>");
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
}
```

**Explicación:**
Este código muestra cómo un Servlet puede responder tanto a solicitudes GET como POST, generando una página HTML simple.

---

## 3. Modelo Requerimiento-Respuesta (Request-Response)

Cuando un cliente (navegador) hace una petición HTTP, envía:
- Un encabezado (información sobre la petición)
- Un método HTTP (GET o POST)
- Datos (como los de un formulario)

**GET:** Los datos se envían en la URL. Es útil para búsquedas o datos no confidenciales.
**POST:** Los datos se envían en el cuerpo del mensaje. Es útil para enviar información confidencial o archivos.

**Explicación:**
El servidor recibe la petición, extrae la información, procesa la lógica (por ejemplo, consulta una base de datos) y responde al cliente con el resultado.

---

## 4. Métodos HTTP más comunes

- **GET:** Los datos se agregan al URL. Limitado en tamaño y menos seguro.
- **POST:** Los datos se envían aparte del URL. Sin límite de tamaño y más seguro.

**Comparativa:**
- GET almacena datos en el historial y permite marcadores (bookmarks).
- POST no almacena datos en el historial y es más privado.
- GET es más rápido pero menos seguro.
- POST es más seguro y permite enviar cualquier tipo de datos.

**Explicación:**
Usa GET para datos públicos y POST para datos privados o grandes.

---

## 5. Ciclo de Vida de un Servlet

El ciclo de vida de un Servlet está controlado por el contenedor web (como Tomcat):
1. Si no existe una instancia, el contenedor carga la clase, crea una instancia y llama al método `init()`.
2. Invoca el método `service()`, pasando los objetos `request` y `response`.
3. Al cerrar la aplicación, el contenedor llama al método `destroy()` para finalizar el Servlet.

**Explicación:**
El contenedor se encarga de crear, inicializar, ejecutar y destruir el Servlet según sea necesario.

---

## 6. Métodos del ciclo de vida

- **init():** Se ejecuta una sola vez al crear el Servlet. Se usa para inicializar recursos (por ejemplo, conectar a la base de datos).
- **service():** Atiende todas las peticiones. No se debe sobrescribir directamente.
- **doGet(), doPost():** Se implementan para definir el comportamiento ante solicitudes GET y POST.
- **destroy():** Se ejecuta antes de eliminar el Servlet. Se usa para liberar recursos (por ejemplo, cerrar conexiones).

**Explicación:**
Estos métodos permiten controlar cómo el Servlet responde a las peticiones y gestiona sus recursos.

---

**Recomendación:**
Repasa cada concepto y observa cómo los Servlets procesan las peticiones y respuestas. En las próximas clases veremos ejemplos prácticos y ejercicios para afianzar estos conocimientos.

---

¡Nos vemos en la próxima clase!
