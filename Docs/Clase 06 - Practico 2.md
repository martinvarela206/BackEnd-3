# Practico 2

## Tienda

### 1. Crear el proyecto nuevo

New Project > Java with Ant > Java Web > Web Application

### 2. Login

Para el login se va a simular la BBDD.
📁:
- index.jsp: Formulario para login.
- welcome.jsp: Pagina de bienvenida que solo pueden ver los usuarios logeados.
- controllers/AuthServlet.java: El controlador para el login.
- model/Auth.java: El javabean para verificar la autenticación.
- db/users.java: Clase con una coleccion de usuarios para simular el login.


```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Tienda</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Iniciar Sesión</h2>
        <form action="AuthServlet" method="post" class="space-y-5">
            <div>
                <label for="usuario" class="block text-sm font-medium text-gray-700">Usuario</label>
                <input type="text" id="usuario" name="usuario" required autocomplete="username"
                    class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Contraseña</label>
                <input type="password" id="password" name="password" required autocomplete="current-password"
                    class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
            </div>
            <button type="submit" class="w-full py-2 px-4 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-700 transition">Ingresar</button>
        </form>
        <% String error = request.getParameter("error");
           if (error != null) { %>
            <div class="mt-4 text-center text-red-600 font-medium">Usuario o contraseña incorrectos</div>
        <% } %>
    </div>
</body>
</html>
```

### 3. Crear los Servlets y JavaBeans

Se necesita servlets para lo que seran los controladores (paquete controllers):
- CarritoServlet
- ProductoServlet
- UsuarioServlet

Se necesitan JavaBeans para lo que sera la lógica de negocio que interactua con la BD (paquete models):
- Carrito
- Item
- Producto
- Usuario

También para simular la BD se van a usar entidades que se corresponderian con las tablas:
- CarritosDOA
- ProductosDOA
- UsuariosDOA

> [!INFO]
> DOA significa Data Access Object. Sirve para separar la lógica de acceso a datos de la lógica de negocio. Cada una de estas clases encapsula un CRUD y abstrae los detalles de la BBDD.

