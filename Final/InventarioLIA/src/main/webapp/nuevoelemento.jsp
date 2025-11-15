<navbar>
    <a href="elementos">Inventario</a> | 
    <a href="movimientos">Movimientos</a> | 
</navbar>

<form action="elementos" method="post">
    <input type="hidden" name="accion" value="crear" />
    <label>Nro LIA: <input type="text" name="nroLia" required></label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj"></label><br>
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
    </label><br>
    <label>Descripción: <input type="text" name="descripcion"></label><br>
    <label>Cantidad: <input type="number" name="cantidad" min="1" required></label><br>
    <button type="submit">Guardar</button>
    <a href="elementos">Cancelar</a>
</form>