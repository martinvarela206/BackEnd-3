
<script>
  import Logo from '../common/Logo.svelte';
  import { user } from '../../stores.js';
  
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
    background: #fbc101;
    color: #111;
    margin-bottom: 32px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    font-family: system-ui, -apple-system, sans-serif;
    display: flex;
    justify-content: center;
    width: 100%;
  }
  .navbar-content {
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 1196px;
    width: 100%;
    padding: 8px 64px;
  }
  .logo-container {
    display: flex;
    align-items: center;
    gap: 12px;
    text-decoration: none;
    cursor: pointer;
  }
  .logo-text {
    font-size: 1.5rem;
    font-weight: bold;
    color: #111;
  }
  .logo-container:hover .logo-text {
    color: #111;
  }
  .navbar-links {
    display: flex;
    align-items: center;
    gap: 32px;
  }
  .navbar a {
    color: #111;
    text-decoration: none;
    font-weight: 500;
    border-bottom: 2px solid transparent;
    transition: border-color 0.2s;
  }
  .navbar a:hover {
    border-bottom-color: #111;
  }
  .navbar .navbar-user {
    font-weight: 600;
    letter-spacing: 0.5px;
    color: #111;
  }
  .navbar .navbar-logout {
    display: flex;
    align-items: center;
    justify-content: center;
    background: #c62828;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    transition: background 0.2s;
    border-bottom: none;
    padding: 0;
  }
  .navbar .navbar-logout:hover {
    background: #8e1c1c;
  }
  .navbar .navbar-logout img {
    width: 22px;
    height: 22px;
    filter: brightness(0) invert(1);
    display: block;
  }

  @media (max-width: 768px) {
    .navbar-content {
      padding: 8px 20px;
    }
    .navbar-links {
      gap: 16px;
      font-size: 0.9rem;
    }
    .logo-text {
      font-size: 1.2rem;
    }
  }
</style>

<nav class="navbar">
  <div class="navbar-content">
    <a href="#/" class="logo-container">
      <Logo variant="dark" />
      <span class="logo-text">Sistema LIA</span>
    </a>
    <div class="navbar-links">
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
        <a href="#/logout" class="navbar-logout" title="Cerrar sesión">
          <img src="/assets/logout.svg" alt="Cerrar sesión" />
        </a>
      {:else}
        <a href="#/login" >Iniciar sesión</a>
      {/if}
    </div>
  </div>
</nav>
