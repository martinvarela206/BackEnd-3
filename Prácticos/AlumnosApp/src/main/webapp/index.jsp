<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.mycompany.alumnosapp.Materia" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>AlumnosApp</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background: #34495e;
            padding: 18px 0;
            text-align: center;
            box-shadow: 0 2px 8px rgba(44,62,80,0.08);
        }
        .navbar a {
            color: #fff;
            text-decoration: none;
            margin: 0 24px;
            font-size: 18px;
            font-weight: 500;
            transition: color 0.2s;
        }
        .navbar a:hover {
            color: #1abc9c;
        }
        .container {
            max-width: 700px;
            margin: 40px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(44,62,80,0.08);
            padding: 32px 40px;
        }
        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        li {
            margin-bottom: 12px;
        }
        .opcion {
            font-size: 20px;
            color: #34495e;
            font-weight: 600;
            margin-bottom: 8px;
        }
        a.facultad-link {
            color: #2980b9;
            text-decoration: none;
            font-size: 16px;
            transition: color 0.2s;
        }
        a.facultad-link:hover {
            color: #1abc9c;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="alumnos">Alumnos</a>
        <a href="consultas">Consultas</a>
        <a href="docentes">Docentes</a>
    </div>
    <div class="container">
        <p class="opcion">Facultades</p>
        <ul>
            <c:forEach var="f" items="${facultades}">
                <li><a class="facultad-link" href="facultad?facultadId=${f.idfacultad}">${f.nombre}</a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
