<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: system-ui, -apple-system, sans-serif;
    }
    .tabla-elementos {
        width: 80%;
        margin: 30px auto;
        border-collapse: collapse;
        box-shadow: 0 2px 8px #bbb;
        background: #fff;
        border-radius: 8px;
        overflow: hidden;
    }
    .tabla-elementos th, .tabla-elementos td {
        padding: 12px 20px;
        border-bottom: 1px solid #eee;
        text-align: left;
    }
    .tabla-elementos th {
        background: #1976d2;
        color: #fff;
        font-weight: 600;
        letter-spacing: 0.05em;
    }
    .tabla-elementos tr:last-child td {
        border-bottom: none;
    }
    .tabla-elementos tr:hover {
        background: #f1f7ff;
        transition: background 0.2s;
    }
    .boton-accion {
        background: #1976d2;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 6px 14px;
        margin: 0 2px;
        cursor: pointer;
        font-size: 0.875rem;
        font-family: system-ui, sans-serif;
        transition: background 0.2s;
    }
    .boton-accion:hover {
        background: #125ea2;
    }
    .enlace-nuevo {
        display: inline-block;
        margin: 18px 0 18px 10%;
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
</style>

<h2 style="text-align:center; margin-top:40px; color:#1976d2;">Lista de Elementos</h2>
<a href="nuevoelemento.jsp" class="enlace-nuevo">Añadir Elemento</a>
<table class="tabla-elementos">
    <thead>
        <tr>
            <th>Nro LIA</th>
            <th>Nro UNSJ</th>
            <th>Tipo</th>
            <th>Descripción</th>
            <th>Cantidad</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="e" items="${lista}">
        <tr>
            <td>${e.nroLia}</td>
            <td>${e.nroUnsj}</td>
            <td>${e.tipo}</td>
            <td><a href="detalleelemento?nroLia=${e.nroLia}">${e.descripcion}</a></td>
            <td>${e.cantidad}</td>
            <td>
                <form action="elementos" method="get" style="display:inline;">
                    <input type="hidden" name="accion" value="editar" />
                    <input type="hidden" name="nroLia" value="${e.nroLia}" />
                    <button type="submit" class="boton-accion">Modificar</button>
                </form>
                <form action="elementos" method="post" style="display:inline;" onsubmit="return confirm('¿Seguro que desea eliminar este elemento?');">
                    <input type="hidden" name="accion" value="eliminar" />
                    <input type="hidden" name="nroLia" value="${e.nroLia}" />
                    <button type="submit" class="boton-accion" style="background:#c62828;">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>