
<script>
import { onMount } from 'svelte';
import { getElementos, deleteElemento } from '../api/inventario.js';
import { user, alerta } from '../stores.js';
let elementos = [];
let error = '';

async function cargar() {
  try {
    elementos = await getElementos();
  } catch (e) {
    error = e.message;
  }
}

onMount(cargar);

async function eliminar(nroLia) {
  if (confirm('¿Seguro que desea eliminar el elemento?')) {
    try {
      await deleteElemento(nroLia);
      alerta.set('Elemento eliminado');
      cargar();
    } catch (e) {
      alerta.set('Error al eliminar: ' + e.message);
    }
  }
}
</script>

<div class="p-4">
  <h2 class="text-2xl font-bold mb-4">Elementos</h2>
  {#if error}
    <div class="text-red-500">{error}</div>
  {:else}
    <table class="min-w-full border">
      <thead>
        <tr class="bg-gray-100">
          <th class="p-2 border">Nro Lia</th>
          <th class="p-2 border">Tipo</th>
          <th class="p-2 border">Descripción</th>
          <th class="p-2 border">Cantidad</th>
          <th class="p-2 border">Acciones</th>
        </tr>
      </thead>
      <tbody>
        {#each elementos as el}
          <tr>
            <td class="p-2 border">{el.nroLia}</td>
            <td class="p-2 border">{el.tipo}</td>
            <td class="p-2 border">{el.descripcion}</td>
            <td class="p-2 border">{el.cantidad}</td>
            <td class="p-2 border">
              <a class="text-blue-700 underline mr-2" href={`#/elemento/${el.nroLia}`}>Ver</a>
              {#if $user && $user.role === 'coordinador'}
                <a class="text-green-700 underline mr-2" href={`#/elemento/editar/${el.nroLia}`}>Editar</a>
                <button class="text-red-600 ml-2" on:click={() => eliminar(el.nroLia)}>Eliminar</button>
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</div>
