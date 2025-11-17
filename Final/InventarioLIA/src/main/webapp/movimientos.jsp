<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
    body {
        margin: 0;
        padding: 0;
    }
    .tabla-movimientos {
        width: 90%;
        margin: 30px auto;
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
        padding: 6px 14px;
        margin: 0 2px;
        cursor: pointer;
        font-size: 0.95em;
        transition: background 0.2s;
        text-decoration: none;
        display: inline-block;
    }
    .boton-accion:hover {
        background: #125ea2;
    }
    .boton-eliminar {
        background: #c62828;
    }
    .boton-eliminar:hover {
        background: #8e1c1c;
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

<h2 style="text-align:center; margin-top:40px; color:#1976d2;">Lista de Movimientos</h2>
<a href="movimientos?accion=nuevo" class="enlace-nuevo">Añadir Movimiento</a>
<table class="tabla-movimientos">
    <thead>
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
    </thead>
    <tbody>
    <c:forEach var="m" items="${lista}" varStatus="status" begin="0" end="${fn:length(lista)-1}" step="1">
        <c:set var="reverseIndex" value="${fn:length(lista) - 1 - status.index}" />
        <c:set var="mov" value="${lista[reverseIndex]}" />
        <tr>
            <td>${mov.id}</td>
            <td>${mov.nroLia.nroLia}</td>
            <td>${mov.nroUnsj}</td>
            <td>${mov.estado}</td>
            <td>${mov.ubicacion}</td>
            <td>${mov.fecha}</td>
            <td>${mov.comentario}</td>
            <td>${mov.userId.nombre}</td>
            <td>
                <a href="movimientos?accion=editar&id=${mov.id}" class="boton-accion">Modificar</a>
                <a href="movimientos?accion=eliminar&id=${mov.id}" class="boton-accion boton-eliminar" onclick="return confirm('¿Seguro que desea eliminar este movimiento?');">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="elementos" class="enlace-volver">Volver a elementos</a>
