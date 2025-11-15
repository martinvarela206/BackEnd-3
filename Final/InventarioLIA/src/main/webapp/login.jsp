<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Iniciar sesión</h2>
<form action="login" method="post">
    <label>Usuario: <input type="text" name="nombre" required></label><br>
    <label>Contraseña: <input type="password" name="password" required></label><br>
    <button type="submit">Ingresar</button>
</form>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>