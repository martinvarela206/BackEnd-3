
<script>
  import { user } from '../stores.js';
  
  // Usar el store global de usuario
  $: currentUser = $user;
  $: safeUser = currentUser || {};
  $: roles = Array.isArray(safeUser && safeUser["roles"]) ? safeUser["roles"] : [];
  $: isLogged = !!currentUser;
  $: nombre = safeUser && typeof safeUser["nombre"] === "string" ? safeUser["nombre"] : '';
  $: puedeVerElementos = roles.includes('tecnico');
  $: puedeVerMovimientos = roles.includes('coordinador');
</script>

<style>
  .navbar {
    background: #1976d2;
    color: #fff;
    padding: 0 0 0 0;
    margin-bottom: 32px;
    box-shadow: 0 2px 8px #bbb;
    font-family: 'Segoe UI', Arial, sans-serif;
  }
  .navbar-content {
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 1100px;
    margin: 0 auto;
    padding: 0 24px;
    height: 54px;
  }
  .navbar-links {
    display: flex;
    align-items: center;
    gap: 18px;
  }
  .navbar a {
    color: #fff;
    text-decoration: none;
    font-weight: 500;
    padding: 7px 16px;
    border-radius: 4px;
    transition: background 0.18s;
    font-size: 1em;
  }
  .navbar a:hover {
    background: #125ea2;
  }
  .navbar .navbar-user {
    font-weight: 600;
    margin-right: 18px;
    letter-spacing: 0.5px;
  }
  .navbar .navbar-logout {
    background: #c62828;
    color: #fff;
    margin-left: 10px;
    transition: background 0.18s;
  }
  .navbar .navbar-logout:hover {
    background: #8e1c1c;
  }
</style>

<nav class="navbar">
  <div class="navbar-content">
    <div class="navbar-links">
      <a href="#/"><b>Inventario LIA</b></a>
      <a href="#/inicio"><b>Inicio</b></a>
      {#if isLogged}
        {#if puedeVerElementos}
          <a href="#/elementos">Elementos</a>
        {/if}
        {#if puedeVerMovimientos}
          <a href="#/movimientos">Movimientos</a>
        {/if}
      {/if}
    </div>
    <div class="navbar-links">
      {#if isLogged}
        <span class="navbar-user">Bienvenido, {#if nombre}{nombre}{:else}{currentUser.username}{/if}</span>
        <a href="#/logout" class="navbar-logout">Cerrar sesión</a>
      {:else}
        <a href="#/login" >Iniciar sesión</a>
      {/if}
    </div>
  </div>
</nav>
