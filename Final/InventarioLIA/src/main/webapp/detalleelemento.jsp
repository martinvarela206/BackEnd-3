<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<navbar>
    <a href="elementos">Inventario</a> | 
    <a href="movimientos">Movimientos</a> | 
</navbar>

<h2>Detalle del Elemento</h2>
<table border="1">
    <tr><th>Nro LIA</th><td>${elemento.nroLia}</td></tr>
    <tr><th>Nro UNSJ</th><td>${elemento.nroUnsj}</td></tr>
    <tr><th>Tipo</th><td>${elemento.tipo}</td></tr>
    <tr><th>Descripción</th><td>${elemento.descripcion}</td></tr>
    <tr><th>Cantidad</th><td>${elemento.cantidad}</td></tr>
</table>

<h3>Movimientos asociados</h3>
<a href="movimientos?accion=nuevo&nroLia=${elemento.nroLia}">Añadir Nuevo Movimiento</a>
<table border="1">
    <tr>
        <th>Fecha</th>
        <th>Estado</th>
        <th>Ubicación</th>
        <th>Comentario</th>
    </tr>
    <c:forEach var="m" items="${movimientos}">
        <tr>
            <td>${m.fecha}</td>
            <td>${m.estado}</td>
            <td>${m.ubicacion}</td>
            <td>${m.comentario}</td>
        </tr>
    </c:forEach>
</table>
<a href="elementos">Volver al listado</a>