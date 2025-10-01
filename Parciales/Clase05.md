# Clase 05: Componentes Web - JSP (Java Server Page)

Bienvenidos a la quinta clase de Backend. Hoy aprenderemos qué son los JSP, cómo funcionan y por qué son útiles para crear páginas web dinámicas en Java EE. Cada concepto se explica de forma sencilla para facilitar el aprendizaje.

---

## 1. ¿Qué son los JSP?

Un JSP (Java Server Page) es un componente dinámico de la plataforma Java EE. Es un documento de texto que puede devolver contenido estático (como HTML) y dinámico (generado por Java) al navegador del cliente.

**Explicación:**
Los JSP permiten mezclar código Java con HTML, lo que facilita la creación de páginas web que cambian según la información o el usuario.

---

## 2. Ejemplo de JSP

```jsp
<body>
<h1>Hola !!!</h1>
<br>
<h2>Hoy es <%= new java.util.Date() %></h2>
</body>
```

**Explicación:**
El código `<%= ... %>` permite mostrar el resultado de una expresión Java directamente en la página web.

---

## 3. ¿Cómo funcionan los JSP?

- El contenedor web busca las etiquetas JSP y genera el código Java correspondiente.
- Las partes estáticas (HTML) se convierten en cadenas de texto Java.
- Las referencias a JavaBeans se traducen en objetos y llamadas a métodos.
- Se construye el código de un Servlet y se compila automáticamente.
- El contenedor ejecuta el Servlet para generar la respuesta al usuario.

**Explicación:**
El proceso convierte el JSP en un Servlet, que luego se ejecuta para crear la página web final.

---

## 4. Ventajas de los JSP

- Separan el contenido (HTML) de la lógica (Java).
- Simplifican el desarrollo de aplicaciones web.
- Permiten reutilizar componentes (JavaBeans).
- Se recompilan automáticamente cuando hay cambios.
- Son fáciles de usar para quienes conocen HTML.
- Funcionan en cualquier sistema operativo (plataforma-independiente).

**Explicación:**
Los JSP hacen que el desarrollo web sea más rápido y organizado, permitiendo modificar la lógica sin cambiar el diseño.

---

## 5. Ventajas de los JSP sobre los Servlets

- Los Servlets requieren escribir mucho código Java para generar HTML.
- Los JSP permiten escribir HTML y solo agregar Java donde sea necesario.
- En el patrón MVC, el Servlet es el controlador y el JSP es la vista.

**Explicación:**
Usar JSP para la presentación y Servlets para el control facilita la organización y el mantenimiento del código.

---

## 6. Elementos JSP

- **Directives:** Proveen información sobre la página JSP (no generan salida).
- **Scripting:** Permiten escribir código Java para manejar objetos y cálculos.
- **Actions:** Realizan acciones como crear objetos, incluir recursos o redirigir peticiones.

**Explicación:**
Estos elementos permiten personalizar y controlar el comportamiento de la página JSP.

---

### Directives

- Sintaxis estándar JSP y sintaxis XML.
- Ejemplo de directiva `page`:
  ```jsp
  <%@ page contentType="text/html; charset=UTF-8" language="java" %>
  ```
- Ejemplo de directiva `include`:
  ```jsp
  <%@ include file="cabecera.jsp" %>
  ```
- Ejemplo de directiva `taglib` para ampliar etiquetas:
  ```jsp
  <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
  ```

### Scripting

- **Declaration:** Permite declarar variables o métodos Java.
  ```jsp
  <%! ArrayList libros = new ArrayList(); %>
  ```
- **Scriptlet:** Permite escribir código Java.
  ```jsp
  <% libros.add(new Libro("Martin Fierro", "Jose Hernandez")); %>
  ```
- **Expression:** Permite mostrar el resultado de una expresión.
  ```jsp
  <%= unLibro.getNombre(); %>
  ```

### Actions

- **useBean:** Asocia una instancia de objeto a una variable.
- **setProperty:** Asigna valores a las propiedades de un bean.
- **getProperty:** Muestra el valor de una propiedad de un bean.
- **include:** Incluye recursos estáticos o dinámicos.
- **forward:** Redirige la petición a otro recurso.
- **plugin, params, element, attribute, body:** Permiten trabajar con plugins y XML.

---

## 7. JSTL (JavaServer Pages Standard Tag Library)

JSTL es una biblioteca de etiquetas estándar que encapsula funcionalidades comunes en aplicaciones web.

- Permite usar etiquetas para tareas como mostrar datos, formatear, acceder a bases de datos y procesar XML.
- Asegura que la aplicación funcione en cualquier contenedor JSP compatible con JSTL.

**Explicación:**
JSTL simplifica el código y mejora la compatibilidad entre diferentes servidores.

---

### Área funcional básica (c)

- `<c:out>`: Muestra el resultado de una expresión.
- `<c:set>`: Asigna un valor a un objeto.
- `<c:remove>`: Elimina una variable.
- `<c:catch>`: Captura excepciones.
- `<c:if>`: Evalúa una condición.
- `<c:choose>`, `<c:when>`, `<c:otherwise>`: Estructuras condicionales.
- `<c:forEach>`: Repite un bloque varias veces.
- `<c:import>`, `<c:url>`, `<c:param>`, `<c:redirect>`: Trabajan con URLs y recursos.

### Área funcional de formateo (fmt)

- `<fmt:message>`: Muestra mensajes.
- `<fmt:formatNumber>`, `<fmt:parseNumber>`: Formatea y convierte números.
- `<fmt:formatDate>`, `<fmt:parseDate>`: Formatea y convierte fechas.
- `<fmt:bundle>`, `<fmt:setLocale>`, `<fmt:requestEncoding>`, `<fmt:timeZone>`, `<fmt:setTimeZone>`: Configuran idioma y zona horaria.

---

## 8. Componentes JavaBeans

- Son clases Java reutilizables que siguen ciertas reglas (constructor sin argumentos, variables privadas, métodos get/set).
- Los JSP pueden usar JavaBeans para manejar datos y lógica de negocio.

**Explicación:**
Los JavaBeans permiten separar la lógica de negocio del diseño de la página, facilitando la reutilización y el mantenimiento.

---

### Ejemplo de uso de JavaBeans en JSP

```jsp
<jsp:useBean id="libro" scope="session" class="modelo.Libros" />
<jsp:setProperty name="libro" property="*" />
<%
 libro.processRequest(request);
%>
```

**Explicación:**
`useBean` crea una instancia del bean si no existe.
`setProperty` asigna valores a sus propiedades usando los parámetros de entrada.

---

## 9. Alcance de los beans

- **page:** El bean está disponible solo en la página actual.
- **session:** El bean se guarda en la sesión del usuario y puede usarse en varias páginas.
- **application:** El bean se guarda en el contexto de la aplicación y es compartido por todos los usuarios.
- **request:** El bean está disponible solo durante la petición actual.

**Explicación:**
El alcance define cuánto tiempo y dónde se puede usar el bean en la aplicación web.

---

**Recomendación:**
Repasa cada concepto y experimenta creando páginas JSP que usen JavaBeans y JSTL. En las próximas clases veremos ejemplos prácticos y ejercicios para afianzar estos conocimientos.

---

¡Nos vemos en la próxima clase!
