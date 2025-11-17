<script>
  import { alerta } from '../stores.js';
  import Navbar from './Navbar.svelte';
  import ElementosList from './ElementosList.svelte';
  import InicioElementos from './InicioElementos.svelte';
  import MovimientosList from './MovimientosList.svelte';
  import ElementoDetalle from './ElementoDetalle.svelte';
  import EditarElementoForm from './EditarElementoForm.svelte';
  import EditarMovimientoForm from './EditarMovimientoForm.svelte';
  import NuevoMovimientoForm from './NuevoMovimientoForm.svelte';
  import NuevoElementoForm from './NuevoElementoForm.svelte';
  import Router from 'svelte-spa-router';

  $: currentAlerta = $alerta;

  // Auto-limpiar la alerta después de 3 segundos
  $: if (currentAlerta) {
    setTimeout(() => {
      alerta.set('');
    }, 3000);
  }

  // Definir rutas internas de la aplicación
  const routes = {
    '/inicio': InicioElementos,
    '/elementos': ElementosList,
    '/movimientos': MovimientosList,
    '/elementos/nuevo': NuevoElementoForm,
    '/elemento/:nroLia': ElementoDetalle,
    '/elemento/editar/:nroLia': EditarElementoForm,
    '/movimiento/editar/:id': EditarMovimientoForm,
    '/movimiento/nuevo/:nroLia': NuevoMovimientoForm,
    '/movimiento/nuevo': NuevoMovimientoForm
  };
</script>

<div class="min-h-screen bg-gray-50">
  <Navbar />
  {#if currentAlerta}
    <div class="max-w-xl mx-auto my-4 p-3 bg-green-100 text-green-800 border border-green-300 rounded">
      {currentAlerta}
    </div>
  {/if}
  <section class="container mx-auto">
    <Router {routes} />
  </section>
</div>

<style>
  div {
    font-family: system-ui, sans-serif;
  }
</style>
