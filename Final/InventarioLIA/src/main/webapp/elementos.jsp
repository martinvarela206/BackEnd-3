<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/jspf/navbar.jspf" %>

<h1>Lista de Elementos</h1>
<a href="nuevoelemento.jsp">Añadir Elemento</a>
<table border="1">
    <tr>
        <th>Nro LIA</th>
        <th>Nro UNSJ</th>
        <th>Tipo</th>
        <th>Descripción</th>
        <th>Cantidad</th>
        <th>Acciones</th>
    </tr>
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
                    <button type="submit">Modificar</button>
                </form> |
                <form action="elementos" method="post" style="display:inline;" onsubmit="return confirm('¿Seguro que desea eliminar este elemento?');">
                    <input type="hidden" name="accion" value="eliminar" />
                    <input type="hidden" name="nroLia" value="${e.nroLia}" />
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>