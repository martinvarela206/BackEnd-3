
<script>
import { onMount } from 'svelte';
import { getMovimientos, deleteMovimiento } from '../api/inventario.js';
import { user, alerta } from '../stores.js';
let movimientos = [];
let error = '';

async function cargar() {
  try {
    movimientos = await getMovimientos();
  } catch (e) {
    error = e.message;
  }
}

onMount(cargar);

async function eliminar(id) {
  if (confirm('¿Seguro que desea eliminar el movimiento?')) {
    try {
      await deleteMovimiento(id);
      alerta.set('Movimiento eliminado');
      cargar();
    } catch (e) {
      alerta.set('Error al eliminar: ' + e.message);
    }
  }
}
</script>

<div>
  <h2 class="text-center mt-10 text-[#1976d2] text-2xl font-semibold">Lista de Movimientos</h2>
  {#if $user && $user.roles && $user.roles.includes('coordinador')}
    <a href="#/movimiento/nuevo" class="inline-block mt-5 ml-[5%] bg-[#43a047] text-white px-5 py-2 rounded font-medium transition-colors duration-200 hover:bg-[#2e7031] no-underline">Añadir Movimiento</a>
  {/if}
  {#if error}
    <div class="text-red-500 text-center mt-4">{error}</div>
  {:else}
    <table class="w-[90%] mx-auto mt-8 shadow-md bg-white rounded-lg overflow-hidden" style="border-collapse: collapse;">
      <thead>
        <tr class="bg-[#1976d2] text-white">
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">ID</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Nro UNSJ</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Estado</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Ubicación</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Fecha</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Comentario</th>
          <th class="py-2.5 px-3.5 text-left font-semibold tracking-wide">Acciones</th>
        </tr>
      </thead>
      <tbody>
        {#each movimientos as m, i}
          <tr class="hover:bg-[#f1f7ff] transition-colors" class:border-b={i < movimientos.length - 1} class:border-gray-200={i < movimientos.length - 1}>
            <td class="py-2.5 px-3.5">{m.id}</td>
            <td class="py-2.5 px-3.5">{m.nroUnsj}</td>
            <td class="py-2.5 px-3.5">{m.estado}</td>
            <td class="py-2.5 px-3.5">{m.ubicacion}</td>
            <td class="py-2.5 px-3.5">{m.fecha}</td>
            <td class="py-2.5 px-3.5">{m.comentario}</td>
            <td class="py-2.5 px-3.5">
              {#if $user && $user.roles && $user.roles.includes('coordinador')}
                <button 
                  on:click={() => window.location.hash = `/movimiento/editar/${m.id}`}
                  class="bg-[#1976d2] text-white border-none rounded px-3.5 py-1.5 mx-0.5 cursor-pointer text-sm transition-colors duration-200 hover:bg-[#125ea2]"
                >
                  Modificar
                </button>
                <button 
                  on:click={() => eliminar(m.id)}
                  class="bg-[#c62828] text-white border-none rounded px-3.5 py-1.5 mx-0.5 cursor-pointer text-sm transition-colors duration-200 hover:bg-[#8e1c1c]"
                >
                  Eliminar
                </button>
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
  <a href="#/elementos" class="inline-block mt-6 ml-[5%] bg-[#bdbdbd] text-[#333] px-5 py-2 rounded font-medium transition-colors duration-200 hover:bg-[#888] hover:text-white no-underline">Volver a elementos</a>
</div>
