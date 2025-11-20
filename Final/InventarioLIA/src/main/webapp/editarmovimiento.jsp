<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: system-ui, -apple-system, sans-serif;
    }
    .formulario {
        width: 420px;
        margin: 40px auto;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 8px #bbb;
        padding: 32px 36px 24px 36px;
    }
    .formulario label {
        display: block;
        margin-bottom: 16px;
        color: #1976d2;
        font-weight: 500;
    }
    .formulario input, .formulario select {
        width: 100%;
        padding: 8px 10px;
        margin-top: 6px;
        border: 1px solid #bdbdbd;
        border-radius: 4px;
        font-size: 1em;
        font-family: system-ui, sans-serif;
        box-sizing: border-box;
        background: #f7fafd;
        transition: border 0.2s;
    }
    .formulario input:focus, .formulario select:focus {
        border: 1.5px solid #1976d2;
        outline: none;
    }
    .boton-form {
        background: #1976d2;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 10px 22px;
        margin-right: 10px;
        cursor: pointer;
        font-size: 1em;
        font-weight: 500;
        font-family: system-ui, sans-serif;
        transition: background 0.2s;
    }
    .boton-form:hover {
        background: #125ea2;
    }
    .boton-cancelar {
        background: #bdbdbd;
        color: #333;
        border: none;
        border-radius: 4px;
        padding: 10px 22px;
        text-decoration: none;
        font-size: 1em;
        font-weight: 500;
        font-family: system-ui, sans-serif;
        transition: background 0.2s;
    }
    .boton-cancelar:hover {
        background: #888;
        color: #fff;
    }
    .mensaje-error {
        color: #c62828;
        background: #ffebee;
        border: 1px solid #c62828;
        border-radius: 4px;
        padding: 10px 16px;
        margin-bottom: 18px;
        text-align: center;
        font-weight: 500;
    }
</style>

<div class="formulario">
    <c:if test="${not empty error}">
        <div class="mensaje-error">${error}</div>
    </c:if>
    <h2 style="text-align:center; color:#1976d2; margin-bottom:24px;">Editar Movimiento</h2>
    <form action="movimientos" method="post" accept-charset="UTF-8">
        <input type="hidden" name="accion" value="modificar" />
        <input type="hidden" name="id" value="${movimiento.id}" />
        <label>Nro LIA:
            <select name="nroLia" required>
                <c:forEach var="e" items="${elementos}">
                    <option value="${e.nroLia}" ${movimiento.nroLia.nroLia == e.nroLia ? 'selected' : ''}>${e.nroLia}</option>
                </c:forEach>
            </select>
        </label>
        <label>Nro UNSJ:
            <input type="text" name="nroUnsj" value="${movimiento.nroUnsj}">
        </label>
        <label>Estado:
            <select name="estado" required>
                <option value="ingresado" ${movimiento.estado == 'ingresado' ? 'selected' : ''}>Ingresado</option>
                <option value="guardado" ${movimiento.estado == 'guardado' ? 'selected' : ''}>Guardado</option>
                <option value="funcionando" ${movimiento.estado == 'funcionando' ? 'selected' : ''}>Funcionando</option>
                <option value="dado de baja" ${movimiento.estado == 'dado de baja' ? 'selected' : ''}>Dado de baja</option>
                <option value="prestado" ${movimiento.estado == 'prestado' ? 'selected' : ''}>Prestado</option>
            </select>
        </label>
        <label>Ubicación:
            <input type="text" name="ubicacion" value="${movimiento.ubicacion}" required>
        </label>
        <label>Comentario:
            <input type="text" name="comentario" value="${movimiento.comentario}">
        </label>
        <label>Usuario:
            <select name="userId" required>
                <c:forEach var="u" items="${usuarios}">
                    <option value="${u.id}" ${movimiento.userId.id == u.id ? 'selected' : ''}>${u.nombre}</option>
                </c:forEach>
            </select>
        </label>
        <button type="submit" class="boton-form">Guardar</button>
        <a href="movimientos" class="boton-cancelar">Cancelar</a>
    </form>
</div>
