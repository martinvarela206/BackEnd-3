<script>
  import { link } from 'svelte-spa-router';
</script>

<style>
  .hero {
    background-image: url('/assets/notebook-wide.png');
    background-size: cover;
    background-position: center;
    height: 540px;
    max-width: 1196px;
    margin: 0 auto;
    display: flex;
    justify-content: flex-end;
    padding: 24px;
  }

  .hero-content {
    display: flex;
    flex-direction: column;
    max-width: 435px;
    padding: 44px 0;
  }

  .hero h2 {
    color: white;
    text-align: left;
    font-size: 1.2rem;
    margin-bottom: 8px;
  }

  .hero h3 {
    color: white;
    font-weight: bold;
    font-size: 2.25rem;
    line-height: 1.2;
    margin-bottom: 12px;
  }

  .login-buttons {
    display: flex;
    gap: 24px;
    justify-content: center;
    margin: 16px 0;
  }

  .btn-solid {
    background: #dba800;
    color: #111;
    padding: 10px 24px;
    border-radius: 4px;
    border: 4px solid #dba800;
    font-weight: 500;
    text-decoration: none;
    transition: all 0.2s;
  }

  .btn-solid:hover {
    background: #fbc101;
    border-color: #fbc101;
  }

  .btn-outline {
    background: rgba(0, 0, 0, 0.25);
    backdrop-filter: blur(8px);
    color: #dba800;
    padding: 10px 24px;
    border-radius: 4px;
    border: 4px solid #dba800;
    font-weight: 500;
    text-decoration: none;
    transition: all 0.2s;
  }

  .btn-outline:hover {
    border-color: #fbc101;
    background: #fbc101;
    color: #111;
  }
</style>

<section class="hero" id="inicio">
  <div class="hero-content">
    <h2>BIENVENIDO AL SISTEMA LIA</h2>
    <h3>Gestiona el Inventario y Tickets de Incidencias del LIA de manera segura y ordenada.</h3>
    <h3>¡Todo bajo control!</h3>
    <div class="login-buttons">
      <a href="#/inicio" class="btn-solid" use:link>Ingresar</a>
      <a href="#/inicio" class="btn-outline" use:link>Registrarse</a>
    </div>
  </div>
</section>
