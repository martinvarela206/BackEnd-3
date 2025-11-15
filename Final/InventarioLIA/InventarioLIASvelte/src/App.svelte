

<script>
  import { onMount } from 'svelte';
  import { route } from './lib/router.js';
  import { user, alerta } from './lib/stores.js';
  import Navbar from './lib/components/Navbar.svelte';
  import LoginForm from './lib/components/LoginForm.svelte';
  import ElementosList from './lib/components/ElementosList.svelte';
  import InicioElementos from './lib/components/InicioElementos.svelte';
  import MovimientosList from './lib/components/MovimientosList.svelte';
  import ElementoForm from './lib/components/ElementoForm.svelte';
  import MovimientoForm from './lib/components/MovimientoForm.svelte';
  import ElementoDetalle from './lib/components/ElementoDetalle.svelte';
  import EditarElementoForm from './lib/components/EditarElementoForm.svelte';
  import EditarMovimientoForm from './lib/components/EditarMovimientoForm.svelte';


  // Usar la suscripción reactiva de Svelte
  $: currentRoute = $route;
  $: currentUser = $user;
  $: currentAlerta = $alerta;

  function handleLogin(e) {
    user.set(e.detail.user);
    window.location.hash = '/';
    alerta.set('Bienvenido ' + e.detail.user.username);
  }
  function handleLogout() {
    user.set(null);
    window.location.hash = '/login';
    alerta.set('Sesión cerrada');
  }
</script>

<main class="min-h-screen bg-gray-50">
  <Navbar {currentUser} />
  {#if currentAlerta}
    <div class="max-w-xl mx-auto my-4 p-3 bg-green-100 text-green-800 border border-green-300 rounded">{currentAlerta}</div>
  {/if}
  <section class="container mx-auto">
    {#if currentRoute === '/login'}
      <LoginForm on:login={handleLogin} />
    {:else if currentRoute === '/elementos'}
      <ElementosList />
      {#if currentUser && currentUser.role === 'coordinador'}
        <ElementoForm onSuccess={() => window.location.reload()} />
      {/if}
    {:else if $route === '/movimientos'}
      <MovimientosList />
      {#if $user && $user.role === 'coordinador'}
        <MovimientoForm onSuccess={() => window.location.reload()} />
      {/if}
    {:else if $route && $route.startsWith('/elemento/editar/')}
      <EditarElementoForm nroLia={$route.split('/')[3]} />
    {:else if $route && $route.startsWith('/movimiento/editar/')}
      <EditarMovimientoForm id={$route.split('/')[3]} />
    {:else if $route && $route.startsWith('/elemento/')}
      <ElementoDetalle nroLia={$route.split('/')[2]} />
    {:else}
      <InicioElementos />
    {/if}
  </section>
</main>

<style>
  main {
    font-family: system-ui, sans-serif;
  }
</style>
