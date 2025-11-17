

<script>
  import { user, alerta } from './lib/stores.js';
  import Navbar from './lib/components/Navbar.svelte';
  import Welcome from './lib/components/Welcome.svelte';
  import ElementosList from './lib/components/ElementosList.svelte';
  import InicioElementos from './lib/components/InicioElementos.svelte';
  import MovimientosList from './lib/components/MovimientosList.svelte';
  import ElementoDetalle from './lib/components/ElementoDetalle.svelte';
  import EditarElementoForm from './lib/components/EditarElementoForm.svelte';
  import EditarMovimientoForm from './lib/components/EditarMovimientoForm.svelte';
  import NuevoMovimientoForm from './lib/components/NuevoMovimientoForm.svelte';
  import Router from 'svelte-spa-router';


  $: currentUser = $user;
  $: currentAlerta = $alerta;

  // Auto-limpiar la alerta después de 3 segundos
  $: if (currentAlerta) {
    setTimeout(() => {
      alerta.set('');
    }, 3000);
  }

  // Definir rutas para svelte-spa-router
  const routes = {
    '/': Welcome,
    '/inicio': InicioElementos,
    '/elementos': ElementosList,
    '/movimientos': MovimientosList,
    '/elemento/editar/:nroLia': EditarElementoForm,
    '/movimiento/editar/:id': EditarMovimientoForm,
    '/movimiento/nuevo/:nroLia': NuevoMovimientoForm,
    '/movimiento/nuevo': NuevoMovimientoForm,
    '/elemento/:nroLia': ElementoDetalle
  };
</script>

  <main class="min-h-screen bg-gray-50">
    <Navbar />
    {#if currentAlerta}
      <div class="max-w-xl mx-auto my-4 p-3 bg-green-100 text-green-800 border border-green-300 rounded">{currentAlerta}</div>
    {/if}
    <section class="container mx-auto">
      <Router {routes} />
    </section>
  </main>

<style>
  main {
    font-family: system-ui, sans-serif;
  }
</style>
