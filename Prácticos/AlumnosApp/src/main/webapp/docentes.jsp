<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Docentes y Materias</title>
</head>
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
    table {
        width: 80%;
        margin: 30px auto 40px auto;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0 2px 8px rgba(44,62,80,0.08);
        border-radius: 8px;
        overflow: hidden;
    }
    th, td {
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
    <a class="back-link" href="index.jsp">Volver al menú principal</a>
</body>
</html>