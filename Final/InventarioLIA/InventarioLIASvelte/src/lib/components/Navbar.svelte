
<script>
  export let currentUser = null;
</script>

<nav class="bg-blue-800 text-white px-6 py-3 flex items-center justify-between">
  <div class="font-bold text-xl">Inventario Lia</div>
  <div class="space-x-4">
    <a href="#/" class="hover:underline">Inicio</a>
    <a href="#/elementos" class="hover:underline">Elementos</a>
    <a href="#/movimientos" class="hover:underline">Movimientos</a>
    {#if currentUser && typeof currentUser === 'object'}
      <span class="ml-4">Hola, {currentUser?.username || currentUser?.nombre || ''} ({currentUser?.role || ''})</span>
      <a href="#/logout" class="ml-4 hover:underline">Salir</a>
    {:else}
      <a href="#/login" class="hover:underline">Login</a>
    {/if}
  </div>
</nav>
