<script>
  import { alerta } from '../../stores.js';
  import Navbar from './Navbar.svelte';
  import Footer from '../common/Footer.svelte';
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

<div class="app-container">
  <Navbar />
  {#if currentAlerta}
    <div class="max-w-xl mx-auto my-4 p-3 bg-[#fbc101] text-[#111] border-2 border-[#dba800] rounded-lg font-medium shadow-md">
      {currentAlerta}
    </div>
  {/if}
  <section class="container mx-auto content-section">
    <Router {routes} />
  </section>
  <Footer dark={true} />
</div>

<style>
  .app-container {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    background: #fef8e7;
    font-family: system-ui, sans-serif;
  }

  .content-section {
    flex: 1;
    padding-bottom: 32px;
  }
</style>
