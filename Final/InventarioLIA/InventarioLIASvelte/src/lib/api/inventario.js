// Obtener la última ubicación de un elemento por nroLia
export async function getUltimaUbicacionElemento(nroLia) {
  const res = await fetch(`${API_BASE}/elementos/${nroLia}/ultima-ubicacion`);
  if (!res.ok) throw new Error('No se pudo obtener la última ubicación');
  return res.json();
}
// Funciones para consumir la API REST de InventarioLia
const API_BASE = 'http://localhost:8080/InventarioLia/api';

export async function getElementos() {
  const res = await fetch(`${API_BASE}/elementos`);
  if (!res.ok) throw new Error('Error al obtener elementos');
  return res.json();
}

export async function getElemento(id) {
  const res = await fetch(`${API_BASE}/elementos/${id}`);
  if (!res.ok) throw new Error('Elemento no encontrado');
  return res.json();
}

export async function createElemento(data) {
  const res = await fetch(`${API_BASE}/elementos`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error('Error al crear elemento');
  return res;
}

export async function updateElemento(id, data) {
  const res = await fetch(`${API_BASE}/elementos/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error('Error al actualizar elemento');
  return res;
}

export async function deleteElemento(id) {
  const res = await fetch(`${API_BASE}/elementos/${id}`, {
    method: 'DELETE'
  });
  if (!res.ok) throw new Error('Error al eliminar elemento');
  return res;
}

// Movimientos
export async function getMovimientos() {
  const res = await fetch(`${API_BASE}/movimientos`);
  if (!res.ok) throw new Error('Error al obtener movimientos');
  return res.json();
}

export async function getMovimiento(id) {
  const res = await fetch(`${API_BASE}/movimientos/${id}`);
  if (!res.ok) throw new Error('Movimiento no encontrado');
  return res.json();
}

export async function createMovimiento(data) {
  const res = await fetch(`${API_BASE}/movimientos`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error('Error al crear movimiento');
  return res;
}

export async function updateMovimiento(id, data) {
  const res = await fetch(`${API_BASE}/movimientos/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error('Error al actualizar movimiento');
  return res;
}

export async function deleteMovimiento(id) {
  const res = await fetch(`${API_BASE}/movimientos/${id}`, {
    method: 'DELETE'
  });
  if (!res.ok) throw new Error('Error al eliminar movimiento');
  return res;
}

// Usuarios y Roles (similares)
export async function getUsuarios() {
  const res = await fetch(`${API_BASE}/usuarios`);
  if (!res.ok) throw new Error('Error al obtener usuarios');
  return res.json();
}

export async function getRoles() {
  const res = await fetch(`${API_BASE}/roles`);
  if (!res.ok) throw new Error('Error al obtener roles');
  return res.json();
}
