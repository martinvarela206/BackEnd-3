# Clase 02: Internet y World Wide Web

Bienvenidos a la segunda clase de Backend. Hoy vamos a conocer los conceptos fundamentales de Internet, la Web y las tecnologías que permiten crear y visualizar páginas web. Cada tema incluye una breve explicación para facilitar el estudio.

---

## 1. ¿Qué es Internet?

Internet es una red mundial de computadoras interconectadas que usan el protocolo TCP/IP para comunicarse. Es descentralizada, lo que significa que no hay un único dueño ni un único servidor central.

**Explicación:**
Internet conecta millones de redes y dispositivos en todo el mundo, permitiendo compartir información y servicios. Su origen fue ARPANET, una red creada en 1969 para conectar universidades en Estados Unidos.

---

## 2. Servicios de Internet (TCP/IP)

Internet ofrece muchos servicios, cada uno con su propio protocolo:
- **Correo electrónico (SMTP):** Permite enviar y recibir mensajes.
- **Transmisión de archivos (FTP, P2P):** Para compartir archivos entre computadoras.
- **Conversaciones en línea (IRC):** Chats en tiempo real.
- **Mensajería instantánea:** Comunicación rápida entre usuarios.
- **Multimedia (VoIP, IPTV):** Llamadas y televisión por internet.
- **Boletines electrónicos (NNTP):** Foros y grupos de noticias.
- **Acceso remoto (SSH, Telnet):** Controlar computadoras a distancia.
- **Juegos en línea:** Videojuegos que se juegan por internet.
- **World Wide Web (HTTP):** Navegación de páginas web.

**Explicación:**
Cada servicio usa un protocolo diferente para funcionar correctamente y comunicarse entre computadoras.

---

## 3. La Web: HTTP sobre TCP/IP

La Web es uno de los servicios más populares de Internet. Utiliza el protocolo HTTP para transferir páginas y recursos.

**Explicación paso a paso:**
1. El usuario escribe una dirección (URL) en el navegador.
2. El navegador envía una solicitud HTTP al servidor.
3. El servidor busca el archivo o programa solicitado.
4. El servidor responde con el contenido (HTML, imagen, etc.).
5. El navegador muestra la página al usuario.

**Ejemplo:**
Cuando visitas una página web, tu navegador y el servidor se comunican usando estos pasos.

---

## 4. World Wide Web Consortium (W3C)

El W3C es una comunidad internacional que define los estándares para el desarrollo web, como HTML, CSS y HTTP.

**Explicación:**
Gracias al W3C, los navegadores y las páginas web funcionan de manera similar en todo el mundo, facilitando la compatibilidad y el acceso.

---

## 5. Desarrollo Web: Frontend y Backend

- **Frontend:** Es la parte de la web que ve el usuario (páginas, aplicaciones, juegos). Se desarrolla con HTML, CSS y JavaScript.
- **Backend:** Es la parte que no ve el usuario. Gestiona bases de datos, correos, archivos y la lógica del servidor.

**Explicación:**
El frontend se encarga de la apariencia y la interacción, mientras que el backend maneja los datos y el funcionamiento interno.

---

## 6. Front-End: Conceptos Clave

- **Maquetación y Responsive Design:** Organizar el contenido y adaptarlo a diferentes dispositivos.
- **Interacción (JavaScript):** Permite que la página responda a acciones del usuario.
- **Gestión de datos (Ajax):** Actualiza información sin recargar la página.
- **Conexión con servicios web:** Permite obtener datos de otros sistemas.
- **Animación (CSS3):** Efectos visuales y transiciones.
- **SEO:** Optimización para buscadores.
- **Accesibilidad (WAI):** Facilita el uso para personas con discapacidades.
- **Usabilidad:** Hace que la web sea fácil e intuitiva de usar.

**Explicación:**
El frontend busca que la web sea atractiva, funcional y accesible para todos los usuarios.

---

## 7. HTML: El Esqueleto del Frontend

HTML es el lenguaje que estructura la información de una página web.

- Contiene la información y la interfaz.
- Define la estructura semántica de los contenidos.

**Evolución:**
- **HTML 2:** Primer estándar, muy básico.
- **HTML 3.2:** Primer estándar recomendado por W3C.
- **HTML 4:** Añade más etiquetas y funciones.
- **XHTML:** Extiende HTML usando reglas de XML.
- **HTML5:** Última versión, orientada a aplicaciones modernas.

**Explicación:**
HTML define qué elementos aparecen en la página y cómo se organizan.

---

## 8. Estructura de un Documento HTML5

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Documento de ejemplo</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>
  <div id="main_wrapper">
    <header></header>
    <div id="main_container"></div>
    <footer></footer>
  </div>
</body>
</html>
```

**Explicación:**
Esta estructura básica define el inicio y fin de la página, la cabecera, el cuerpo y las áreas principales.

---

## 9. DOM: Modelo de Objetos del Documento

El DOM es una representación de la página web como una estructura de objetos. Permite modificar el contenido y la estructura de la página desde el código.

**Explicación:**
Gracias al DOM, los lenguajes como JavaScript pueden cambiar la página en tiempo real, agregando o quitando elementos.

---

## 10. CSS: Estilos para la Web

CSS es el lenguaje que define la apariencia de la página web (colores, fuentes, tamaños, posiciones, animaciones).

**Ejemplos de selectores:**
- `etiqueta { }` — Aplica a todas las etiquetas de ese tipo.
- `#identificador { }` — Aplica a un elemento con ese id.
- `.clase { }` — Aplica a todos los elementos con esa clase.
- `etiqueta.clase { }` — Aplica a una etiqueta con una clase específica.
- `etiqueta .clase { }` — Aplica a elementos anidados.
- `etiqueta, .clase { }` — Aplica a varios elementos.
- `#identificador.modificador { }` — Aplica estilos modificados a un id.

**Evolución:**
CSS ha añadido bordes, fondos, sombras, transformaciones y animaciones para mejorar el diseño visual.

**Explicación:**
CSS permite separar el contenido (HTML) de la presentación (estilos), facilitando el diseño y la personalización.

---

## 11. JavaScript: Interactividad en la Web

JavaScript es el lenguaje que permite que la página web interactúe con el usuario y maneje contenido dinámico.

- No está relacionado con Java.
- Es débilmente tipado (no exige definir el tipo de datos).
- Permite crear páginas dinámicas y responder a acciones del usuario.

**Explicación:**
JavaScript hace posible que la web sea interactiva, actualizando el contenido sin recargar la página.

---

## 12. ID y CLASS en HTML, CSS y JavaScript

- **ID:** Identificador único para un elemento. Se usa para aplicar estilos o acceder desde JavaScript.
- **CLASS:** Permite agrupar varios elementos bajo un mismo nombre para aplicar estilos o comportamientos.

**Explicación:**
Usar id y class ayuda a organizar y personalizar los elementos de la página, facilitando el diseño y la programación.

---

**Recomendación:**
Repasa cada concepto y experimenta creando una página sencilla usando HTML, CSS y JavaScript. En las próximas clases veremos ejemplos prácticos y ejercicios para afianzar estos conocimientos.

---

¡Nos vemos en la próxima clase!
