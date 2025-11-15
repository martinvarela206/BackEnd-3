<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<navbar>
    <a href="elementos">Inventario</a> | 
    <a href="movimientos">Movimientos</a> | 
</navbar>

<h2>Editar Movimiento</h2>
<form action="movimientos" method="post">
    <input type="hidden" name="accion" value="modificar" />
    <input type="hidden" name="id" value="${movimiento.id}" />
    <label>Nro LIA:
        <select name="nroLia" required>
            <c:forEach var="e" items="${elementos}">
                <option value="${e.nroLia}" ${movimiento.nroLia == e.nroLia ? 'selected' : ''}>${e.nroLia}</option>
            </c:forEach>
        </select>
    </label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj" value="${movimiento.nroUnsj}"></label><br>
    <label>Estado:
        <select name="estado" required>
            <option value="ingresado" ${movimiento.estado == 'ingresado' ? 'selected' : ''}>Ingresado</option>
            <option value="guardado" ${movimiento.estado == 'guardado' ? 'selected' : ''}>Guardado</option>
            <option value="funcionando" ${movimiento.estado == 'funcionando' ? 'selected' : ''}>Funcionando</option>
            <option value="dado de baja" ${movimiento.estado == 'dado de baja' ? 'selected' : ''}>Dado de baja</option>
            <option value="prestado" ${movimiento.estado == 'prestado' ? 'selected' : ''}>Prestado</option>
        </select>
    </label><br>
    <label>Ubicación: <input type="text" name="ubicacion" value="${movimiento.ubicacion}" required></label><br>
    <label>Comentario: <input type="text" name="comentario" value="${movimiento.comentario}"></label><br>
    <label>Usuario:
        <select name="userId" required>
            <c:forEach var="u" items="${usuarios}">
                <option value="${u.id}" ${movimiento.userId.id == u.id ? 'selected' : ''}>${u.nombre}</option>
            </c:forEach>
        </select>
    </label><br>
    <button type="submit">Guardar</button>
    <a href="movimientos">Cancelar</a>
</form>
