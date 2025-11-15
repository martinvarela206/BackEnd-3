<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.alerta}">
        <div style="color:red;">${sessionScope.alerta}</div>
        <c:remove var="alerta" scope="session" />
</c:if>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
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
        padding: 12px 18px;
        border-bottom: 1px solid #eee;
        text-align: left;
    }
    .tabla-elementos th {
        background: #1976d2;
        color: #fff;
        font-weight: 600;
        letter-spacing: 1px;
    }
    .tabla-elementos tr:last-child td {
        border-bottom: none;
    }
    .tabla-elementos tr:hover {
        background: #f1f7ff;
    }
</style>

<h2 style="text-align:center; margin-top:40px; color:#1976d2;">Resumen de Elementos</h2>
<c:if test="${not empty elementos}">
<table class="tabla-elementos">
    <thead>
        <tr>
            <th>Nro LIA</th>
            <th>Descripción</th>
            <th>Ubicación actual</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="e" items="${elementos}">
            <tr>
                <td>${e.nroLia}</td>
                <td>${e.descripcion}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty ubicaciones[e.nroLia]}">
                            ${ubicaciones[e.nroLia]}
                        </c:when>
                        <c:otherwise>
                            <span style="color:#888;">Sin movimientos</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:if>
