<script>
import { onMount } from 'svelte';
import { getElementos, getUltimaUbicacionElemento } from '../api/inventario.js';
let elementosConUbicacion = [];
let error = '';
let cargando = true;

// Cargar elementos y movimientos
async function cargar() {
  try {
    const elementos = await getElementos();
    const promises = elementos.map(async (el) => {
      try {
        const resp = await getUltimaUbicacionElemento(el.nroLia);
        return { ...el, ubicacion: resp.ubicacion ?? '--' };
      } catch (e) {
        return { ...el, ubicacion: '-e' };
      }
    });
    elementosConUbicacion = await Promise.all(promises);
  } catch (e) {
    error = e.message;
  } finally {
    cargando = false;
  }
}

onMount(cargar);



</script>

<div class="p-4">
  <h2 class="text-2xl font-bold mb-4">Inventario - Resumen</h2>
  {#if error}
    <div class="text-red-500">{error}</div>
  {:else if cargando}
    <div class="text-gray-500">Cargando datos...</div>
  {:else}
    <table class="min-w-full border">
      <thead>
        <tr class="bg-gray-100">
          <th class="p-2 border">Nro LIA</th>
          <th class="p-2 border">Descripción</th>
          <th class="p-2 border">Ubicación actual</th>
        </tr>
      </thead>
      <tbody>
        {#each elementosConUbicacion as el}
          <tr>
            <td class="p-2 border">{el.nroLia}</td>
            <td class="p-2 border">{el.descripcion}</td>
            <td class="p-2 border">{el.ubicacion}</td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</div>
