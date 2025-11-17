
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

<div>
  <h2 class="text-center mt-10 text-[#dba800] text-2xl font-semibold">Lista de Elementos</h2>
  {#if $user && $user.roles && $user.roles.includes('tecnico')}
    <a href="#/elementos/nuevo" class="inline-block mt-5 ml-[10%] bg-[#dba800] text-[#111] px-5 py-2 rounded font-semibold transition-colors duration-200 hover:bg-[#fbc101] no-underline border-2 border-[#dba800] hover:border-[#fbc101]">Añadir Elemento</a>
  {/if}
  {#if error}
    <div class="text-red-500 text-center mt-4">{error}</div>
  {:else}
    <table class="w-4/5 mx-auto mt-8 shadow-md bg-white rounded-lg overflow-hidden" style="border-collapse: collapse;">
      <thead>
        <tr class="bg-[#fbc101] text-[#111]">
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Nro LIA</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Tipo</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Descripción</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Cantidad</th>
          <th class="py-3 px-5 text-left font-semibold tracking-wide">Acciones</th>
        </tr>
      </thead>
      <tbody>
        {#each elementos as el, i}
          <tr class="hover:bg-[#fef8e7] transition-colors" class:border-b={i < elementos.length - 1} class:border-gray-200={i < elementos.length - 1}>
            <td class="py-3 px-5 text-[#111]">{el.nroLia}</td>
            <td class="py-3 px-5 text-[#111]">{el.tipo}</td>
            <td class="py-3 px-5">
              <a href={`#/elemento/${el.nroLia}`} class="text-[#dba800] hover:text-[#fbc101] font-medium hover:underline">{el.descripcion}</a>
            </td>
            <td class="py-3 px-5 text-[#111]">{el.cantidad}</td>
            <td class="py-3 px-5">
              {#if $user && $user.roles && $user.roles.includes('coordinador')}
                <button 
                  on:click={() => window.location.hash = `/elemento/editar/${el.nroLia}`}
                  class="bg-[#dba800] text-[#111] border-2 border-[#dba800] rounded px-3.5 py-1.5 mx-0.5 cursor-pointer text-sm font-medium transition-colors duration-200 hover:bg-[#fbc101] hover:border-[#fbc101]"
                >
                  Modificar
                </button>
                <button 
                  on:click={() => eliminar(el.nroLia)}
                  class="bg-[#c62828] text-white border-2 border-[#c62828] rounded px-3.5 py-1.5 mx-0.5 cursor-pointer text-sm font-medium transition-colors duration-200 hover:bg-[#8e1c1c] hover:border-[#8e1c1c]"
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
</div>
