<script>
  import { alerta, user } from '../stores.js';
  import { createElemento, createMovimiento } from '../api/inventario.js';

  let elemento = {
    nroLia: '',
    nroUnsj: '',
    tipo: 'cpu',
    descripcion: '',
    cantidad: 1
  };

  let error = '';

  async function guardar() {
    try {
      if (!elemento.nroLia || !elemento.tipo || !elemento.cantidad || elemento.cantidad < 1) {
        error = 'Complete los campos requeridos correctamente';
        return;
      }
      
      // Crear el elemento
      await createElemento(elemento);
      
      // Crear automáticamente el movimiento inicial
      const movimientoInicial = {
        nroLia: { nroLia: elemento.nroLia },
        userId: { id: $user.id },
        fecha: new Date().toISOString().split('T')[0], // Fecha actual en formato YYYY-MM-DD
        ubicacion: 'Administración',
        comentario: 'Ingreso Inicial',
        estado: 'ingresado'
      };
      
      await createMovimiento(movimientoInicial);
      
      alerta.set('Elemento creado exitosamente');
      window.location.hash = '/elementos';
    } catch (e) {
      error = 'Error al crear el elemento: ' + e.message;
    }
  }

  function cancelar() {
    window.location.hash = '/elementos';
  }
</script>

<div class="formulario">
  {#if error}
    <div class="mensaje-error">{error}</div>
  {/if}
  <form on:submit|preventDefault={guardar}>
    <label>
      Nro LIA:
      <input type="text" bind:value={elemento.nroLia} required />
    </label>
    <label>
      Nro UNSJ:
      <input type="text" bind:value={elemento.nroUnsj} />
    </label>
    <label>
      Tipo:
      <select bind:value={elemento.tipo} required>
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
    <label>
      Descripción:
      <input type="text" bind:value={elemento.descripcion} />
    </label>
    <label>
      Cantidad:
      <input type="number" bind:value={elemento.cantidad} min="1" required />
    </label>
    <button type="submit" class="boton-form">Guardar</button>
    <button type="button" on:click={cancelar} class="boton-cancelar">Cancelar</button>
  </form>
</div>

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
  .formulario input,
  .formulario select {
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
  .formulario input:focus,
  .formulario select:focus {
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
