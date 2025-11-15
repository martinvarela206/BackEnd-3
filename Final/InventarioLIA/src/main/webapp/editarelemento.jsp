<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<style>
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
    <form action="elementos" method="post">
        <input type="hidden" name="accion" value="modificar" />
        <input type="hidden" name="nroLia" value="${elemento.nroLia}" />
        <label>Nro LIA: <b>${elemento.nroLia}</b></label>
        <label>Nro UNSJ:
            <input type="text" name="nroUnsj" value="${elemento.nroUnsj}">
        </label>
        <label>Tipo:
            <select name="tipo">
                <option value="cpu" ${elemento.tipo == 'cpu' ? 'selected' : ''}>CPU</option>
                <option value="monitor" ${elemento.tipo == 'monitor' ? 'selected' : ''}>Monitor</option>
                <option value="switch" ${elemento.tipo == 'switch' ? 'selected' : ''}>Switch</option>
                <option value="router" ${elemento.tipo == 'router' ? 'selected' : ''}>Router</option>
                <option value="impresora" ${elemento.tipo == 'impresora' ? 'selected' : ''}>Impresora</option>
                <option value="teclado" ${elemento.tipo == 'teclado' ? 'selected' : ''}>Teclado</option>
                <option value="mouse" ${elemento.tipo == 'mouse' ? 'selected' : ''}>Mouse</option>
                <option value="proyector" ${elemento.tipo == 'proyector' ? 'selected' : ''}>Proyector</option>
                <option value="otro" ${elemento.tipo == 'otro' ? 'selected' : ''}>Otro</option>
            </select>
        </label>
        <label>Descripción:
            <input type="text" name="descripcion" value="${elemento.descripcion}">
        </label>
        <label>Cantidad:
            <input type="number" name="cantidad" min="1" value="${elemento.cantidad}" required>
        </label>
        <button type="submit" class="boton-form">Guardar</button>
        <a href="elementos" class="boton-cancelar">Cancelar</a>
    </form>
</div>