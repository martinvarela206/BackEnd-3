<script>
import { createEventDispatcher } from 'svelte';
let username = '';
let password = '';
let error = '';
const dispatch = createEventDispatcher();

async function login(e) {
  e.preventDefault();
  error = '';
  // Aquí deberías llamar a la API de login real
  if (username === 'admin' && password === 'admin') {
    dispatch('login', { user: { username, role: 'coordinador' } });
  } else {
    error = 'Usuario o contraseña incorrectos';
  }
}
</script>

<div class="formulario-login">
  <form on:submit|preventDefault={login}>
    <label class="block mb-1" for="username">Usuario</label>
    <input id="username" class="border rounded px-2 py-1 w-full" type="text" bind:value={username} required />
    <label class="block mb-1" for="password">Contraseña</label>
    <input id="password" class="border rounded px-2 py-1 w-full" type="password" bind:value={password} required />
    {#if error}
      <div class="mensaje-error">{error}</div>
    {/if}
    <button class="boton-login" type="submit">Ingresar</button>
  </form>
</div>

<style>
  .formulario-login {
    width: 340px;
    margin: 60px auto 0 auto;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 2px 8px #bbb;
    padding: 32px 36px 24px 36px;
  }
  .formulario-login label {
    display: block;
    margin-bottom: 16px;
    color: #1976d2;
    font-weight: 500;
  }
  .formulario-login input {
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
  .formulario-login input:focus {
    border: 1.5px solid #1976d2;
    outline: none;
  }
  .boton-login {
    background: #1976d2;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 10px 22px;
    margin-top: 10px;
    cursor: pointer;
    font-size: 1em;
    font-weight: 500;
    transition: background 0.2s;
    width: 100%;
  }
  .boton-login:hover {
    background: #125ea2;
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
