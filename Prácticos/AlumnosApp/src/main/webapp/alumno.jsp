<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mycompany.alumnosapp.Alumno" %>
<%@ page import="com.mycompany.alumnosapp.Carrera" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Alumnos</title>
</head>
<body>
    <h1>Lista de Alumnos</h1>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Registro</th><th>Carrera</th><th>Acciones</th></tr>
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
                        <input type="submit" value="Eliminar" onclick="return confirm('¿Seguro que desea eliminar este alumno?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar Alumno</h2>
    <form method="post" action="alumno">
        <input type="hidden" name="accion" value="agregar">
        Nombre: <input type="text" name="nombre" required><br>
        Registro: <input type="text" name="registro" required><br>
        Carrera:
        <select name="fkIdcarrera" required>
            <c:forEach var="c" items="${carreras}">
                <option value="${c.idcarrera}">${c.nombre}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>