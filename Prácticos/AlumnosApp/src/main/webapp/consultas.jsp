<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Consultas Especiales</title>
</head>
<body>
    <h1>Mesas de examen del turno de julio</h1>
    <table border="1">
        <tr>
            <th>Materia</th>
            <th>Alumno</th>
            <th>Nota</th>
            <th>Fecha</th>
        </tr>
        <c:forEach var="row" items="${mesasJulio}">
            <tr>
                <td>${row[0].materia.nombre}</td>
                <td>${row[1].nombre}</td>
                <td>${row[0].nota}</td>
                <td>${row[0].fecha}</td>
            </tr>
        </c:forEach>
    </table>

    <h1>Alumnos que no rindieron ninguna materia en 2025</h1>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Registro</th>
        </tr>
        <c:forEach var="a" items="${alumnosSinExamenes}">
            <tr>
                <td>${a.nombre}</td>
                <td>${a.registro}</td>
            </tr>
        </c:forEach>
    </table>

    <h1>Docentes que dictan más de dos materias</h1>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Cargo</th>
            <th>Cantidad de materias</th>
        </tr>
        <c:forEach var="d" items="${docentesMasDeDosMaterias}">
            <tr>
                <td>${d.nombre}</td>
                <td>${d.cargo}</td>
                <td>${fn:length(d.materiaCollection)}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>