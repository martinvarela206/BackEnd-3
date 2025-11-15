<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<form action="elementos" method="post">
    <input type="hidden" name="accion" value="modificar" />
    <input type="hidden" name="nroLia" value="${elemento.nroLia}" />
    <label>Nro LIA: <b>${elemento.nroLia}</b></label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj" value="${elemento.nroUnsj}"></label><br>
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
    </label><br>
    <label>Descripción: <input type="text" name="descripcion" value="${elemento.descripcion}"></label><br>
    <label>Cantidad: <input type="number" name="cantidad" min="1" value="${elemento.cantidad}" required></label><br>
    <button type="submit">Guardar</button>
    <a href="elementos">Cancelar</a>
</form>