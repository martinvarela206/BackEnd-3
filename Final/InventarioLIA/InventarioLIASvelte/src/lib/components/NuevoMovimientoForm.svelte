<script>
import { onMount } from 'svelte';
import { createMovimiento, getElementos, getUsuarios } from '../api/inventario.js';
import { alerta } from '../stores.js';
export let params = {};
let elementos = [];
let usuarios = [];
let movimiento = {
  nroLia: '',
  nroUnsj: '',
  estado: 'ingresado',
  ubicacion: '',
  comentario: '',
  userId: ''
};
let error = '';

$: nroLia = params.nroLia;

onMount(async () => {
  try {
    elementos = await getElementos();
    // Si hay un nroLia en la URL, preseleccionarlo
    if (nroLia) {
      movimiento.nroLia = nroLia;
    }
    // Obtener usuarios (si el endpoint existe)
    try {
      usuarios = await getUsuarios();
    } catch (e) {
      console.warn('No se pudieron obtener usuarios:', e);
      // Si no hay endpoint de usuarios, crear uno de prueba
      usuarios = [{ id: 1, nombre: 'Usuario Demo' }];
      movimiento.userId = 1;
    }
  } catch (e) {
    error = e.message;
  }
});

async function handleSubmit(e) {
  e.preventDefault();
  error = '';
  try {
    // Crear el objeto con la estructura que espera el backend
    const movimientoData = {
      nroUnsj: movimiento.nroUnsj || null,
      estado: movimiento.estado,
      ubicacion: movimiento.ubicacion,
      comentario: movimiento.comentario || null,
      fecha: new Date().toISOString(),
      nroLia: { nroLia: movimiento.nroLia },
      userId: { id: parseInt(movimiento.userId) }
    };
    await createMovimiento(movimientoData);
    alerta.set('Movimiento creado exitosamente');
    if (nroLia) {
      window.location.hash = `/elemento/${nroLia}`;
    } else {
      window.location.hash = '/movimientos';
    }
  } catch (e) {
    error = e.message;
  }
}

function cancelar() {
  if (nroLia) {
    window.location.hash = `/elemento/${nroLia}`;
  } else {
    window.location.hash = '/movimientos';
  }
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
    color: #1976d2;
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
    border: 1.5px solid #1976d2;
    outline: none;
  }
  .boton-form {
    background: #1976d2;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 10px 22px;
    margin-right: 10px;
    cursor: pointer;
    font-size: 1em;
    font-weight: 500;
    transition: background 0.2s;
  }
  .boton-form:hover {
    background: #125ea2;
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

<div class="formulario">
  {#if error}
    <div class="mensaje-error">{error}</div>
  {/if}
  <h2 style="text-align:center; color:#1976d2; margin-bottom:24px;">Nuevo Movimiento</h2>
  <form on:submit={handleSubmit}>
    <label>Nro LIA:
      <select bind:value={movimiento.nroLia} required>
        <option value="">Seleccione un elemento</option>
        {#each elementos as e}
          <option value={e.nroLia}>{e.nroLia}</option>
        {/each}
      </select>
    </label>
    <label>Nro UNSJ:
      <input type="text" bind:value={movimiento.nroUnsj}>
    </label>
    <label>Estado:
      <select bind:value={movimiento.estado} required>
        <option value="ingresado">Ingresado</option>
        <option value="guardado">Guardado</option>
        <option value="funcionando">Funcionando</option>
        <option value="dado de baja">Dado de baja</option>
        <option value="prestado">Prestado</option>
      </select>
    </label>
    <label>Ubicación:
      <input type="text" bind:value={movimiento.ubicacion} required>
    </label>
    <label>Comentario:
      <input type="text" bind:value={movimiento.comentario}>
    </label>
    <label>Usuario:
      <select bind:value={movimiento.userId} required>
        <option value="">Seleccione un usuario</option>
        {#each usuarios as u}
          <option value={u.id}>{u.nombre}</option>
        {/each}
      </select>
    </label>
    <button type="submit" class="boton-form">Guardar</button>
    <button type="button" class="boton-cancelar" on:click={cancelar}>Cancelar</button>
  </form>
</div>