<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <form action="elementos" method="post" accept-charset="UTF-8">
        <input type="hidden" name="accion" value="crear" />
        <label>Nro LIA:
            <input type="text" name="nroLia" required>
        </label>
        <label>Nro UNSJ:
            <input type="text" name="nroUnsj">
        </label>
        <label>Tipo:
            <select name="tipo" required>
                <option value="cpu">CPU</option>
                <option value="monitor">Monitor</option>
                <option value="switch">Switch</option>
                <option value="router">Router</option>
                <option value="impresora">Impresora</option>
                <option value="teclado">Teclado</option>
                <option value="mouse">Mouse</option>
                <option value="proyector">Proyector</option>
                <option value="otro">Otro</option>
            </select>
        </label>
        <label>Descripción:
            <input type="text" name="descripcion">
        </label>
        <label>Cantidad:
            <input type="number" name="cantidad" min="1" required>
        </label>
        <button type="submit" class="boton-form">Guardar</button>
        <a href="elementos" class="boton-cancelar">Cancelar</a>
    </form>
</div>