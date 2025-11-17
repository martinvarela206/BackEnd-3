<script>
import { onMount } from 'svelte';
import { getElemento, updateElemento } from '../../api/inventario.js';
import { alerta } from '../../stores.js';
export let params = {};
let elemento = null;
let error = '';

$: nroLia = params.nroLia;

onMount(async () => {
  try {
    if (nroLia) {
      elemento = await getElemento(nroLia);
    }
  } catch (e) {
    error = e.message;
  }
});

$: if (nroLia && elemento === null) {
  getElemento(nroLia).then(el => elemento = el).catch(e => error = e.message);
}

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

function cancelar() {
  window.location.hash = '/elementos';
}
</script>

<style>
  .formulario {
    width: 420px;
    margin: 40px auto;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 2px 8px #bbb;
    padding: 32px 36px 24px 36px;
  }
  .formulario label {
    display: block;
    margin-bottom: 16px;
    color: #dba800;
    font-weight: 500;
  }
  .formulario input, .formulario select {
    width: 100%;
    padding: 8px 10px;
    margin-top: 6px;
    border: 1px solid #bdbdbd;
    border-radius: 4px;
    font-size: 1em;
    box-sizing: border-box;
    background: #f7fafd;
    transition: border 0.2s;
  }
  .formulario input:focus, .formulario select:focus {
    border: 1.5px solid #dba800;
    outline: none;
  }
  .boton-form {
    background: #dba800;
    color: #111;
    border: 2px solid #dba800;
    border-radius: 4px;
    padding: 10px 22px;
    margin-right: 10px;
    cursor: pointer;
    font-size: 1em;
    font-weight: 500;
    transition: background 0.2s;
  }
  .boton-form:hover {
    background: #fbc101;
    border-color: #fbc101;
  }
  .boton-cancelar {
    background: #bdbdbd;
    color: #333;
    border: none;
    border-radius: 4px;
    padding: 10px 22px;
    cursor: pointer;
    font-size: 1em;
    font-weight: 500;
    transition: background 0.2s;
  }
  .boton-cancelar:hover {
    background: #888;
    color: #fff;
  }
  .mensaje-error {
    color: #c62828;
    background: #ffebee;
    border: 1px solid #c62828;
    border-radius: 4px;
    padding: 10px 16px;
    margin-bottom: 18px;
    text-align: center;
    font-weight: 500;
  }
</style>

{#if elemento}
<div class="formulario">
  {#if error}
    <div class="mensaje-error">{error}</div>
  {/if}
  <form on:submit={handleSubmit}>
    <div style="margin-bottom: 16px; color: #dba800; font-weight: 500;">Nro LIA: <b>{elemento.nroLia}</b></div>
    <label>Nro UNSJ:
      <input type="text" bind:value={elemento.nroUnsj}>
    </label>
    <label>Tipo:
      <select bind:value={elemento.tipo}>
        <option value="cpu">CPU</option>
        <option value="monitor">Monitor</option>
        <option value="switch">Switch</option>
        <option value="router">Router</option>
        <option value="impresora">Impresora</option>
        <option value="teclado">Teclado</option>
        <option value="mouse">Mouse</option>
        <option value="proyector">Proyector</option>
        <option value="otro">Otro</option>
      </select>
    </label>
    <label>Descripción:
      <input type="text" bind:value={elemento.descripcion}>
    </label>
    <label>Cantidad:
      <input type="number" min="1" bind:value={elemento.cantidad} required>
    </label>
    <button type="submit" class="boton-form">Guardar</button>
    <button type="button" class="boton-cancelar" on:click={cancelar}>Cancelar</button>
  </form>
</div>
{:else if error}
<div class="mensaje-error" style="width: 420px; margin: 40px auto;">{error}</div>
{/if}
