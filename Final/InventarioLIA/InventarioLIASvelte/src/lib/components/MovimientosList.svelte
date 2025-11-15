
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

<div class="p-4">
  <h2 class="text-2xl font-bold mb-4">Movimientos</h2>
  {#if error}
    <div class="text-red-500">{error}</div>
  {:else}
    <table class="min-w-full border">
      <thead>
        <tr class="bg-gray-100">
          <th class="p-2 border">ID</th>
          <th class="p-2 border">Nro UNSJ</th>
          <th class="p-2 border">Estado</th>
          <th class="p-2 border">Ubicación</th>
          <th class="p-2 border">Fecha</th>
          <th class="p-2 border">Comentario</th>
          <th class="p-2 border">Acciones</th>
        </tr>
      </thead>
      <tbody>
        {#each movimientos as m}
          <tr>
            <td class="p-2 border">{m.id}</td>
            <td class="p-2 border">{m.nroUnsj}</td>
            <td class="p-2 border">{m.estado}</td>
            <td class="p-2 border">{m.ubicacion}</td>
            <td class="p-2 border">{m.fecha}</td>
            <td class="p-2 border">{m.comentario}</td>
            <td class="p-2 border">
              {#if $user && $user.role === 'coordinador'}
                <a class="text-green-700 underline mr-2" href={`#/movimiento/editar/${m.id}`}>Editar</a>
                <button class="text-red-600 ml-2" on:click={() => eliminar(m.id)}>Eliminar</button>
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</div>
