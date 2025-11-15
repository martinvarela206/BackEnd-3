<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
    .tabla-detalle {
        width: 420px;
        margin: 30px auto 18px auto;
        border-collapse: collapse;
        box-shadow: 0 2px 8px #bbb;
        background: #fff;
        border-radius: 8px;
        overflow: hidden;
    }
    .tabla-detalle th, .tabla-detalle td {
        padding: 10px 16px;
        border-bottom: 1px solid #eee;
        text-align: left;
    }
    .tabla-detalle th {
        background: #1976d2;
        color: #fff;
        font-weight: 600;
        width: 160px;
    }
    .tabla-detalle tr:last-child td {
        border-bottom: none;
    }
    .tabla-movimientos {
        width: 90%;
        margin: 18px auto 0 auto;
        border-collapse: collapse;
        box-shadow: 0 2px 8px #bbb;
        background: #fff;
        border-radius: 8px;
        overflow: hidden;
    }
    .tabla-movimientos th, .tabla-movimientos td {
        padding: 10px 14px;
        border-bottom: 1px solid #eee;
        text-align: left;
    }
    .tabla-movimientos th {
        background: #1976d2;
        color: #fff;
        font-weight: 600;
        letter-spacing: 1px;
    }
    .tabla-movimientos tr:last-child td {
        border-bottom: none;
    }
    .tabla-movimientos tr:hover {
        background: #f1f7ff;
    }
    .enlace-nuevo {
        display: inline-block;
        margin: 18px 0 18px 5%;
        background: #43a047;
        color: #fff;
        padding: 8px 18px;
        border-radius: 5px;
        text-decoration: none;
        font-weight: 500;
        transition: background 0.2s;
    }
    .enlace-nuevo:hover {
        background: #2e7031;
    }
    .boton-accion {
        background: #1976d2;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 7px 18px;
        margin: 0 4px;
        cursor: pointer;
        font-size: 1em;
        font-weight: 500;
        transition: background 0.18s, box-shadow 0.18s;
        text-decoration: none;
        display: inline-block;
        box-shadow: 0 1px 4px #b3b3b3;
    }
    .boton-accion:hover:not([disabled]) {
        background: #125ea2;
        box-shadow: 0 2px 8px #aaa;
    }
    .boton-eliminar {
        background: #c62828;
    }
    .boton-eliminar:hover:not([disabled]) {
        background: #8e1c1c;
    }
    .boton-accion[disabled], .boton-accion[disabled]:hover {
        opacity: 0.5;
        cursor: not-allowed;
        background: #bdbdbd;
        color: #fff;
        box-shadow: none;
    }
    .enlace-volver {
        display: inline-block;
        margin: 24px 0 0 5%;
        background: #bdbdbd;
        color: #333;
        padding: 8px 18px;
        border-radius: 5px;
        text-decoration: none;
        font-weight: 500;
        transition: background 0.2s;
    }
    .enlace-volver:hover {
        background: #888;
        color: #fff;
    }
</style>

<h2 style="text-align:center; margin-top:40px; color:#1976d2;">Detalle del Elemento</h2>
<table class="tabla-detalle">
    <tr><th>Nro LIA</th><td>${elemento.nroLia}</td></tr>
    <tr><th>Nro UNSJ</th><td>${elemento.nroUnsj}</td></tr>
    <tr><th>Tipo</th><td>${elemento.tipo}</td></tr>
    <tr><th>Descripción</th><td>${elemento.descripcion}</td></tr>
    <tr><th>Cantidad</th><td>${elemento.cantidad}</td></tr>
</table>

<h3 style="text-align:center; color:#1976d2; margin-top:32px;">Movimientos asociados</h3>
<a href="movimientos?accion=nuevo&nroLia=${elemento.nroLia}" class="enlace-nuevo">Añadir Nuevo Movimiento</a>
<table class="tabla-movimientos">
    <thead>
        <tr>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Ubicación</th>
            <th>Comentario</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="m" items="${movimientos}" varStatus="status">
        <tr>
            <td>${m.fecha}</td>
            <td>${m.estado}</td>
            <td>${m.ubicacion}</td>
            <td>${m.comentario}</td>
            <td>
                <c:if test="${fn:contains(pageScope.rolesString, 'coordinador')}">
                    <a href="movimientos?accion=editar&id=${m.id}" class="boton-accion" title="Modificar movimiento">Modificar</a>
                    <c:choose>
                        <c:when test="${status.last}">
                            <button class="boton-accion boton-eliminar" title="El primer movimiento solo se elimina borrando el elemento" disabled>Eliminar</button>
                        </c:when>
                        <c:otherwise>
                            <a href="movimientos?accion=eliminar&id=${m.id}" class="boton-accion boton-eliminar" title="Eliminar movimiento" onclick="return confirm('¿Seguro que desea eliminar este movimiento?');">Eliminar</a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="elementos" class="enlace-volver">Volver al listado</a>