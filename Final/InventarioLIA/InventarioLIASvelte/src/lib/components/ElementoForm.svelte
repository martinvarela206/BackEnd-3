<script>
import { createElemento, updateElemento } from '../api/inventario.js';
export let elemento = { nroLia: '', tipo: '', descripcion: '', cantidad: 1 };
export let isEdit = false;
export let onSuccess = () => {};
let error = '';

async function handleSubmit(e) {
  e.preventDefault();
  error = '';
  try {
    if (isEdit) {
      await updateElemento(elemento.nroLia, elemento);
    } else {
      await createElemento(elemento);
    }
    onSuccess();
  } catch (e) {
    error = e.message;
  }
}
</script>

<form class="max-w-md mx-auto bg-white p-6 rounded shadow" on:submit|preventDefault={handleSubmit}>
  <h2 class="text-xl font-bold mb-4">{isEdit ? 'Editar' : 'Nuevo'} Elemento</h2>
  <div class="mb-3">
    <label class="block mb-1" for="nroLia">Nro LIA</label>
    <input id="nroLia" class="border rounded px-2 py-1 w-full" bind:value={elemento.nroLia} disabled={isEdit} required />
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
  <button class="bg-blue-700 text-white px-4 py-2 rounded" type="submit">{isEdit ? 'Actualizar' : 'Crear'}</button>
</form>
