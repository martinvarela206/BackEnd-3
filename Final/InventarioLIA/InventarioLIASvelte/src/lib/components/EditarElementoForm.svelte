<script>
import { onMount } from 'svelte';
import { getElemento, updateElemento } from '../api/inventario.js';
import { alerta } from '../stores.js';
export let nroLia;
let elemento = null;
let error = '';

onMount(async () => {
  try {
    elemento = await getElemento(nroLia);
  } catch (e) {
    error = e.message;
  }
});

async function handleSubmit(e) {
  e.preventDefault();
  error = '';
  try {
    await updateElemento(nroLia, elemento);
    alerta.set('Elemento actualizado');
    window.location.hash = '/elementos';
  } catch (e) {
    error = e.message;
  }
}
</script>

{#if elemento}
<form class="max-w-md mx-auto bg-white p-6 rounded shadow" on:submit|preventDefault={handleSubmit}>
  <h2 class="text-xl font-bold mb-4">Editar Elemento</h2>
  <div class="mb-3">
    <label class="block mb-1" for="nroLia">Nro LIA</label>
    <input id="nroLia" class="border rounded px-2 py-1 w-full" value={elemento.nroLia} disabled />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="tipo">Tipo</label>
    <input id="tipo" class="border rounded px-2 py-1 w-full" bind:value={elemento.tipo} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="descripcion">Descripción</label>
    <input id="descripcion" class="border rounded px-2 py-1 w-full" bind:value={elemento.descripcion} required />
  </div>
  <div class="mb-3">
    <label class="block mb-1" for="cantidad">Cantidad</label>
    <input id="cantidad" type="number" min="1" class="border rounded px-2 py-1 w-full" bind:value={elemento.cantidad} required />
  </div>
  {#if error}
    <div class="text-red-500 mb-2">{error}</div>
  {/if}
  <button class="bg-blue-700 text-white px-4 py-2 rounded" type="submit">Actualizar</button>
</form>
{:else if error}
<div class="text-red-500">{error}</div>
{/if}
