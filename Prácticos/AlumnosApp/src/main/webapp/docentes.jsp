<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Docentes y Materias</title>
</head>
<body>
    <h1>Docentes y las materias que dictan</h1>
    <table border="1">
        <tr>
            <th>Docente</th>
            <th>Cargo</th>
            <th>Materias que dicta</th>
        </tr>
        <c:forEach var="d" items="${docentes}">
            <tr>
                <td>${d.nombre}</td>
                <td>${d.cargo}</td>
                <td>
                    <c:forEach var="m" items="${d.materiaCollection}">
                        ${m.nombre}<br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>