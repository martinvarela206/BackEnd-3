<%@ page import="java.util.List, com.mycompany.alumnosapp.Materia" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>AlumnosApp</title></head>
<body>
    <li><a href="#">
       <p class="opcion">Facultades</p>
   </a>
   <ul>
       <c:forEach var="f" items="${facultades}">
           <li><a href="facultad?facultadId=${f.idfacultad}">${f.nombre}</a></li>
       </c:forEach>
   </ul>
   </li>
</body>
</html>
