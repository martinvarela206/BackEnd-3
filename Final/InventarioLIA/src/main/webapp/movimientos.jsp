<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<navbar>
    <a href="elementos">Inventario</a> | 
    <a href="movimientos">Movimientos</a> | 
</navbar>

<h1>Lista de Movimientos</h1>
<a href="movimientos?accion=nuevo">Añadir Movimiento</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Estado</th>
        <th>Ubicación</th>
        <th>Fecha</th>
        <th>Comentario</th>
        <th>Usuario</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="m" items="${lista}">
        <tr>
            <td>${m.id}</td>
            <td>${m.nroLia}</td>
            <td>${m.nroUnsj}</td>
            <td>${m.estado}</td>
            <td>${m.ubicacion}</td>
            <td>${m.fecha}</td>
            <td>${m.comentario}</td>
            <td>${m.userId.nombre}</td>
            <td>
                <a href="movimientos?accion=editar&id=${m.id}">Modificar</a> |
                <a href="movimientos?accion=eliminar&id=${m.id}" onclick="return confirm('¿Seguro que desea eliminar este movimiento?');">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="elementos">Volver a elementos</a>
