<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Carreras de la Facultad</title>
</head>
<body>
    <h1>Carreras disponibles en la facultad: ${facultad.nombre}</h1>
    <ul>
        <c:forEach var="c" items="${carreras}">
            <li>${c.nombre}</li>
        </c:forEach>
    </ul>
    <a href="index.jsp">Volver al menú principal</a>
</body>
</html>