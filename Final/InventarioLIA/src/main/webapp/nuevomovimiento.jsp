<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<h2>Nuevo Movimiento</h2>
<form action="movimientos" method="post">
    <input type="hidden" name="accion" value="crear" />
    <label>Nro LIA:
        <select name="nroLia" required>
            <c:forEach var="e" items="${elementos}">
                <option value="${e.nroLia}">${e.nroLia}</option>
            </c:forEach>
        </select>
    </label><br>
    <label>Nro UNSJ: <input type="text" name="nroUnsj"></label><br>
    <label>Estado:
        <select name="estado" required>
            <option value="ingresado">Ingresado</option>
            <option value="guardado">Guardado</option>
            <option value="funcionando">Funcionando</option>
            <option value="dado de baja">Dado de baja</option>
            <option value="prestado">Prestado</option>
        </select>
    </label><br>
    <label>Ubicación: <input type="text" name="ubicacion" required></label><br>
    <label>Comentario: <input type="text" name="comentario"></label><br>
    <label>Usuario:
        <select name="userId" required>
            <c:forEach var="u" items="${usuarios}">
                <option value="${u.id}">${u.nombre}</option>
            </c:forEach>
        </select>
    </label><br>
    <button type="submit">Guardar</button>
    <a href="movimientos">Cancelar</a>
</form>
