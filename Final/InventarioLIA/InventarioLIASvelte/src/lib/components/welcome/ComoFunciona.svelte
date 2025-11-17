<style>
  .section-how {
    margin: 48px 0;
    padding: 32px 16px;
  }

  .container {
    max-width: 1196px;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    gap: 12px;
  }

  .section-how h2 {
    color: #dba800;
    font-size: 1.2rem;
  }

  .section-how h3 {
    color: white;
    font-weight: 900;
    font-size: 2.25rem;
    max-width: 500px;
    margin-bottom: 16px;
  }

  .steps {
    display: flex;
    justify-content: space-between;
    gap: 20px;
  }

  .card {
    display: flex;
    flex-direction: column;
    max-width: 260px;
    align-items: center;
    gap: 12px;
    transition: transform 0.3s;
  }

  .card:hover {
    transform: scale(1.1);
  }

  .card-icon {
    background: #dba800;
    width: 160px;
    height: 160px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
  }

  .card-icon img {
    width: 64px;
    height: 64px;
  }

  .card h4 {
    color: white;
    text-align: center;
    font-size: 1.25rem;
    font-weight: 500;
  }

  .card p {
    text-align: center;
    padding: 0 16px;
    color: white;
    font-weight: 300;
  }
</style>

<section class="section-how" id="sistema-lia">
  <div class="container">
    <h2>COMO FUNCIONA</h2>
    <h3>Sin complicaciones, solo resultados. ¡Gestión simple y control total!</h3>
    <div class="steps">
      <div class="card">
        <div class="card-icon">
          <img src="/assets/user-gear.svg" alt="Usuario" />
        </div>
        <h4>Ingreso de Usuario</h4>
        <p>Accede al inventario fácilmente a través del sistema de usuarios para tener todo a la mano.</p>
      </div>
      <div class="card">
        <div class="card-icon">
          <img src="/assets/eye.svg" alt="Revisión" />
        </div>
        <h4>Revisión de Tickets</h4>
        <p>Revisa los tickets de forma rápida y eficiente para organizar y planificar tus tareas diarias.</p>
      </div>
      <div class="card">
        <div class="card-icon">
          <img src="/assets/keyboard.svg" alt="Respuesta" />
        </div>
        <h4>Responder Tickets</h4>
        <p>Resuelve los problemas reportados y responde a los tickets con las soluciones implementadas.</p>
      </div>
      <div class="card">
        <div class="card-icon">
          <img src="/assets/box-open.svg" alt="Inventario" />
        </div>
        <h4>Controlar el Inventario</h4>
        <p>Gestiona el inventario mediante solicitudes de componentes y equipos para solucionar problemas.</p>
      </div>
    </div>
  </div>
</section>
