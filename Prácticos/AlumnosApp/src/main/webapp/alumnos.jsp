<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mycompany.alumnosapp.Alumno" %>
<%@ page import="com.mycompany.alumnosapp.Carrera" %>
<%@ page import="java.util.List" %>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Alumnos</title>

<style>
    body {
        font-family: 'Segoe UI', Arial, sans-serif;
        background: #f4f6f8;
        margin: 0;
        padding: 0;
    }

    h1,
    h2 {
        color: #2c3e50;
        text-align: center;
        margin-top: 30px;
    }

    table {
        width: 80%;
        margin: 30px auto;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0 2px 8px rgba(44, 62, 80, 0.08);
        border-radius: 8px;
        overflow: hidden;
    }

    th,
    td {
        padding: 12px 18px;
        text-align: left;
    }

    th {
        background: #34495e;
        color: #fff;
        font-weight: 600;
        border-bottom: 2px solid #2c3e50;
    }

    tr:nth-child(even) {
        background: #f2f2f2;
    }

    tr:hover {
        background: #eaf1fb;
    }

    form {
        margin: 0;
    }

    input[type="text"] {
        padding: 8px;
        border: 1px solid #bfc9d1;
        border-radius: 4px;
        margin-bottom: 10px;
        width: 220px;
        font-size: 15px;
    }

    select {
        padding: 8px;
        border: 1px solid #bfc9d1;
        border-radius: 4px;
        font-size: 15px;
        margin-bottom: 10px;
    }

    input[type="submit"] {
        background: #2980b9;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 8px 18px;
        font-size: 15px;
        cursor: pointer;
        transition: background 0.2s;
        margin-top: 8px;
    }

    input[type="submit"]:hover {
        background: #1c5d99;
    }

    .form-container {
        width: 400px;
        margin: 0 auto;
        background: #fff;
        padding: 24px 32px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(44, 62, 80, 0.08);
        margin-bottom: 40px;
    }

    .back-link {
        display: block;
        width: fit-content;
        margin: 0 auto 24px auto;
        background: #2980b9;
        color: #fff;
        text-decoration: none;
        padding: 10px 24px;
        border-radius: 4px;
        font-size: 16px;
        transition: background 0.2s;
    }

    .back-link:hover {
        background: #1c5d99;
    }
</style>
</head>

<body>
    <h1>Lista de Alumnos</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Registro</th>
            <th>Carrera</th>
            <th>Acciones</th>
        </tr>
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
                        <input type="submit" value="Eliminar"
                            onclick="return confirm('Seguro que desea eliminar este alumno?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar Alumno</h2>
    <div class="form-container">
        <form method="post" action="alumno">
            <input type="hidden" name="accion" value="agregar">
            <label for="nombre">Nombre:</label><br>
            <input type="text" id="nombre" name="nombre" required><br>
            <label for="registro">Registro:</label><br>
            <input type="text" id="registro" name="registro" required><br>
            <label for="fkIdcarrera">Carrera:</label><br>
            <select id="fkIdcarrera" name="fkIdcarrera" required>
                <c:forEach var="c" items="${carreras}">
                    <option value="${c.idcarrera}">${c.nombre}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Agregar">
        </form>
    </div>
    <a class="back-link" href="index.jsp">Volver al menú principal</a>
</body>

</html>