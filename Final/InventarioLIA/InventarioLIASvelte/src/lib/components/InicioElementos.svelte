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

<div>
  <h2 class="text-center mt-10 text-[#dba800] text-2xl font-semibold">Resumen de Elementos</h2>
  {#if error}
    <div class="text-red-500 text-center mt-4">{error}</div>
  {:else if cargando}
    <div class="text-gray-500 text-center mt-4">Cargando datos...</div>
  {:else if elementosConUbicacion.length > 0}
    <table class="w-4/5 mx-auto mt-8 shadow-md bg-white rounded-lg overflow-hidden" style="border-collapse: collapse;">
      <thead>
        <tr class="bg-[#fbc101] text-[#111]">
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Nro LIA</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Descripción</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Ubicación actual</th>
        </tr>
      </thead>
      <tbody>
        {#each elementosConUbicacion as el, i}
          <tr class="hover:bg-[#fef8e7] transition-colors" class:border-b={i < elementosConUbicacion.length - 1} class:border-gray-200={i < elementosConUbicacion.length - 1}>
            <td class="py-3 px-5 text-[#111]">{el.nroLia}</td>
            <td class="py-3 px-5 text-[#111]">{el.descripcion}</td>
            <td class="py-3 px-5 text-[#111]">
              {#if el.ubicacion === '--' || el.ubicacion === '-e'}
                <span class="text-gray-500">Sin movimientos</span>
              {:else}
                {el.ubicacion}
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</div>
