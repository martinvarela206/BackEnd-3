<script>
import { onMount } from 'svelte';
import { getElemento, getMovimientos } from '../api/inventario.js';
export let nroLia;
let elemento = null;
let movimientos = [];
let error = '';

onMount(async () => {
  try {
    elemento = await getElemento(nroLia);
    movimientos = (await getMovimientos()).filter(m => m.nroLia === nroLia);
  } catch (e) {
    error = e.message;
  }
});
</script>

<div class="p-4">
  {#if error}
    <div class="text-red-500">{error}</div>
  {:else if elemento}
    <h2 class="text-2xl font-bold mb-4">Detalle de Elemento</h2>
    <table class="tabla-detalle mb-6">
      <tbody>
        <tr><th>Nro LIA</th><td>{elemento.nroLia}</td></tr>
        <tr><th>Tipo</th><td>{elemento.tipo}</td></tr>
        <tr><th>Descripción</th><td>{elemento.descripcion}</td></tr>
        <tr><th>Cantidad</th><td>{elemento.cantidad}</td></tr>
      </tbody>
    </table>
    <h3 class="text-xl font-semibold mb-2">Movimientos</h3>
    <table class="tabla-movimientos">
      <thead>
        <tr>
          <th>ID</th>
          <th>Estado</th>
          <th>Ubicación</th>
          <th>Fecha</th>
          <th>Comentario</th>
        </tr>
      </thead>
      <tbody>
        {#each movimientos as m}
          <tr>
            <td>{m.id}</td>
            <td>{m.estado}</td>
            <td>{m.ubicacion}</td>
            <td>{m.fecha}</td>
            <td>{m.comentario}</td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</div>

<style>
  .tabla-detalle {
    width: 420px;
    margin: 30px auto 18px auto;
    border-collapse: collapse;
    box-shadow: 0 2px 8px #bbb;
    background: #fff;
    border-radius: 8px;
    overflow: hidden;
  }
  .tabla-detalle th, .tabla-detalle td {
    padding: 10px 16px;
    border-bottom: 1px solid #eee;
    text-align: left;
  }
  .tabla-detalle th {
    background: #1976d2;
    color: #fff;
    font-weight: 600;
    width: 160px;
  }
  .tabla-detalle tr:last-child td {
    border-bottom: none;
  }
  .tabla-movimientos {
    width: 90%;
    margin: 18px auto 0 auto;
    border-collapse: collapse;
    box-shadow: 0 2px 8px #bbb;
    background: #fff;
    border-radius: 8px;
    overflow: hidden;
  }
  .tabla-movimientos th, .tabla-movimientos td {
    padding: 10px 14px;
    border-bottom: 1px solid #eee;
    text-align: left;
  }
  .tabla-movimientos th {
    background: #1976d2;
    color: #fff;
    font-weight: 600;
    letter-spacing: 1px;
  }
  .tabla-movimientos tr:last-child td {
    border-bottom: none;
  }
  .tabla-movimientos tr:hover {
    background: #f1f7ff;
  }
</style>
