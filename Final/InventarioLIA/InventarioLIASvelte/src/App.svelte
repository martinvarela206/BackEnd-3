

<script>
  import Welcome from './lib/components/Welcome.svelte';
  import Inventario from './lib/components/Inventario.svelte';
  import Router from 'svelte-spa-router';

  // Definir rutas principales de la aplicación
  const routes = {
    '/': Welcome,
    '*': Inventario
  };
</script>

<main class="min-h-screen">
  <Router {routes} />
</main>

<style>
  main {
    font-family: system-ui, sans-serif;
  }
</style>
