<script>
import { onMount } from 'svelte';
import { getMovimiento, updateMovimiento } from '../api/inventario.js';
import { alerta } from '../stores.js';
export let id;
let movimiento = null;
let error = '';

onMount(async () => {
  try {
    movimiento = await getMovimiento(id);
  } catch (e) {
    error = e.message;
  }
});

async function handleSubmit(e) {
  e.preventDefault();
  error = '';
  try {
    await updateMovimiento(id, movimiento);
    alerta.set('Movimiento actualizado');
    window.location.hash = '/movimientos';
  } catch (e) {
    error = e.message;
  }
}
</script>

{#if movimiento}
<form class="max-w-md mx-auto bg-white p-6 rounded shadow" on:submit|preventDefault={handleSubmit}>
  <h2 class="text-xl font-bold mb-4">Editar Movimiento</h2>
  <div class="mb-3">
    <label class="block mb-1" for="id">ID</label>
    <input id="id" class="border rounded px-2 py-1 w-full" value={movimiento.id} disabled />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="nroUnsj">Nro UNSJ</label>
    <input id="nroUnsj" class="border rounded px-2 py-1 w-full" bind:value={movimiento.nroUnsj} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="estado">Estado</label>
    <input id="estado" class="border rounded px-2 py-1 w-full" bind:value={movimiento.estado} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="ubicacion">Ubicación</label>
    <input id="ubicacion" class="border rounded px-2 py-1 w-full" bind:value={movimiento.ubicacion} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="fecha">Fecha</label>
    <input id="fecha" type="date" class="border rounded px-2 py-1 w-full" bind:value={movimiento.fecha} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="comentario">Comentario</label>
    <input id="comentario" class="border rounded px-2 py-1 w-full" bind:value={movimiento.comentario} />
  </div>
  {#if error}
    <div class="text-red-500 mb-2">{error}</div>
  {/if}
  <button class="bg-blue-700 text-white px-4 py-2 rounded" type="submit">Actualizar</button>
</form>
{:else if error}
<div class="text-red-500">{error}</div>
{/if}
