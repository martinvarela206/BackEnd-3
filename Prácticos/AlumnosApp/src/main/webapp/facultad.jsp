<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carreras de la Facultad</title>
<style>
    body {
        font-family: 'Segoe UI', Arial, sans-serif;
        background: #f4f6f8;
        margin: 0;
        padding: 0;
    }
    h1 {
        color: #2c3e50;
        text-align: center;
        margin-top: 40px;
        margin-bottom: 32px;
    }
    .carreras-list {
        max-width: 400px;
        margin: 0 auto 40px auto;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(44,62,80,0.08);
        padding: 24px 32px;
    }
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    li {
        font-size: 18px;
        color: #34495e;
        padding: 10px 0;
        border-bottom: 1px solid #eaeaea;
    }
    li:last-child {
        border-bottom: none;
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
    <h1>Carreras disponibles en la facultad: ${facultad.nombre}</h1>
        <div class="carreras-list">
            <ul>
                <c:forEach var="c" items="${carreras}">
                    <li>${c.nombre}</li>
                </c:forEach>
            </ul>
        </div>
        <a class="back-link" href="index.jsp">Volver al menú principal</a>
</body>
</html>