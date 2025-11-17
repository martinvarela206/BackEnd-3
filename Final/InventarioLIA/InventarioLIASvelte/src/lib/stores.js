import { writable } from 'svelte/store';

// Simulación de usuario logeado para pruebas
export const user = writable({
  id: 1,
  nombre: 'Admin Demo',
  username: 'admin',
  roles: ['user_admin', 'coordinador', 'tecnico']
});
export const alerta = writable('');
