<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .formulario-login {
        width: 340px;
        margin: 60px auto 0 auto;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 8px #bbb;
        padding: 32px 36px 24px 36px;
    }
    .formulario-login label {
        display: block;
        margin-bottom: 16px;
        color: #1976d2;
        font-weight: 500;
    }
    .formulario-login input {
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
    .formulario-login input:focus {
        border: 1.5px solid #1976d2;
        outline: none;
    }
    .boton-login {
        background: #1976d2;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 10px 22px;
        margin-top: 10px;
        cursor: pointer;
        font-size: 1em;
        font-weight: 500;
        transition: background 0.2s;
        width: 100%;
    }
    .boton-login:hover {
        background: #125ea2;
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

<div class="formulario-login">
    <h2 style="text-align:center; color:#1976d2; margin-bottom:24px;">Iniciar sesión</h2>
    <c:if test="${not empty error}">
        <div class="mensaje-error">${error}</div>
    </c:if>
    <form action="login" method="post">
        <label>Usuario:
            <input type="text" name="nombre" required>
        </label>
        <label>Contraseña:
            <input type="password" name="password" required>
        </label>
        <button type="submit" class="boton-login">Ingresar</button>
    </form>
</div>