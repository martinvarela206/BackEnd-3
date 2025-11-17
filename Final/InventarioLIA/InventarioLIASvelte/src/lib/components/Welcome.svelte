<script>
  import { link } from 'svelte-spa-router';
  
  // Smooth scroll para las anclas
  function handleAnchorClick(e) {
    const href = e.currentTarget.getAttribute('href');
    if (href.startsWith('#')) {
      e.preventDefault();
      const element = document.querySelector(href);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    }
  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  :global(body) {
    background: #111;
    color: white;
    font-family: system-ui, -apple-system, sans-serif;
    scroll-behavior: smooth;
  }

  :global(html) {
    scroll-behavior: smooth;
  }

  @keyframes fadeInTop {
    from {
      opacity: 0;
      transform: translateY(-20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  /* Header */
  header {
    background: #fbc101;
    display: flex;
    justify-content: center;
    width: 100%;
    animation: fadeInTop ease-in 1s;
  }

  .header-container {
    padding: 8px 64px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1196px;
    width: 100%;
  }

  .logo-container {
    display: flex;
    align-items: center;
    gap: 12px;
  }

  .logo-icon {
    width: 64px;
    height: 64px;
  }

  .logo-text {
    font-size: 1.5rem;
    font-weight: bold;
    color: #111;
  }

  .nav-links {
    display: flex;
    gap: 32px;
    align-items: center;
  }

  .nav-link {
    color: #111;
    text-decoration: none;
    font-weight: 500;
    padding: 8px 0;
    border-bottom: 2px solid transparent;
    transition: border-color 0.2s;
  }

  .nav-link:hover {
    border-bottom-color: #111;
  }

  .nav-login {
    color: #111;
    text-decoration: none;
    font-weight: 500;
    padding: 8px 16px;
    border-radius: 4px;
    transition: background 0.2s;
  }

  .nav-login:hover {
    background: #dba800;
  }

  /* Hero Section */
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

  /* How it works Section */
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

  /* LIA Section */
  .section-lia {
    height: 520px;
    background: #dba800;
    display: flex;
    justify-content: center;
    margin-top: 48px;
  }

  .lia-container-left {
    width: 400px;
    display: flex;
    margin: 0 44px;
    position: relative;
  }

  .lia-img-1 {
    height: 520px;
    transform: translateY(-16px);
    margin-right: 16px;
    transition: transform 0.3s;
  }

  .lia-img-1:hover {
    transform: translateY(-16px) scale(1.1);
  }

  .lia-img-2 {
    height: 520px;
    transform: translateY(16px);
    margin-right: 16px;
    transition: transform 0.3s;
  }

  .lia-img-2:hover {
    transform: translateY(16px) scale(1.1);
  }

  .lia-img-3 {
    height: 185px;
    width: auto;
    position: absolute;
    left: 190px;
    top: 64px;
    filter: drop-shadow(0 4px 6px rgba(0, 0, 0, 0.3));
    transition: transform 0.3s;
  }

  .lia-img-3:hover {
    transform: scale(1.1);
  }

  .lia-container-right {
    width: 400px;
    display: flex;
    margin: 0 44px;
    flex-direction: column;
    position: relative;
  }

  .lia-top {
    margin: 32px 0;
  }

  .lia-top h2 {
    color: #111;
    font-size: 1.2rem;
    margin-bottom: 8px;
  }

  .lia-top h3 {
    color: white;
    font-weight: 900;
    font-size: 2.25rem;
    max-width: 500px;
    margin-bottom: 12px;
  }

  .lia-top p {
    color: #111;
    line-height: 1.6;
  }

  .lia-bottom {
    position: relative;
  }

  .lia-bottom img:first-child {
    height: 250px;
    position: absolute;
    top: 0;
  }

  .lia-bottom img:last-child {
    height: 160px;
    position: absolute;
    top: -40px;
    right: 64px;
  }

  /* Cards Section */
  .section-cards {
    display: flex;
    margin: 48px auto;
    padding: 64px 0;
    gap: 40px;
    justify-content: space-evenly;
    max-width: 1196px;
  }

  .card-button {
    background: #dba800;
    border-radius: 12px;
    width: 280px;
    height: 280px;
    display: flex;
    flex-direction: column;
    gap: 8px;
    justify-content: center;
    align-items: center;
    transition: transform 0.3s;
    cursor: pointer;
    text-decoration: none;
  }

  .card-button:hover {
    transform: scale(1.1);
  }

  .card-button h4 {
    width: 96px;
    text-align: center;
    color: #111;
    font-size: 1rem;
    font-weight: bold;
    margin: 0;
  }

  .card-icon-circle {
    width: 90px;
    height: 90px;
    border-radius: 50%;
    border: 4px solid #111;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .card-icon-circle img {
    width: 50px;
    height: 50px;
  }

  .card-text-container {
    display: flex;
    align-items: center;
    gap: 8px;
  }

  .card-button p {
    width: 128px;
    text-align: center;
    color: #111;
    font-size: 0.95rem;
    font-weight: 300;
    margin: 0;
  }

  .card-arrow {
    width: 20px;
    height: 20px;
  }

  /* Footer */
  footer {
    background: #111;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 16px;
  }

  .footer-top {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    max-width: 1196px;
    width: 100%;
  }

  .institucional {
    display: flex;
    gap: 16px;
    padding: 32px 0;
    margin-left: auto;
  }

  .institucional img {
    height: 40px;
  }

  .footer-line {
    max-width: 1196px;
    height: 2px;
    background: rgba(219, 168, 0, 0.5);
    width: 100%;
    margin: 32px 0;
  }

  .footer-bottom {
    display: flex;
    gap: 16px;
    align-items: center;
    justify-content: space-between;
    max-width: 1196px;
    width: 100%;
    margin: 32px 0;
  }

  .footer-bottom p {
    font-size: 0.75rem;
    color: #dba800;
  }

  @media (max-width: 768px) {
    .steps {
      flex-direction: column;
      align-items: center;
    }

    .section-lia {
      flex-direction: column;
      height: auto;
    }

    .lia-container-left,
    .lia-container-right {
      width: 100%;
      margin: 20px;
    }

    .section-cards {
      flex-direction: column;
      align-items: center;
    }

    .footer-top,
    .footer-bottom {
      flex-direction: column;
      text-align: center;
    }

    .institucional {
      margin: 0;
    }
  }
</style>

<!-- Header -->
<header id="header">
  <div class="header-container">
    <div class="logo-container">
      <img src="/assets/app-logo.svg" alt="Logo" class="logo-icon" />
      <span class="logo-text">Sistema LIA</span>
    </div>
    <nav class="nav-links">
      <a href="#inicio" class="nav-link" on:click={handleAnchorClick}>Inicio</a>
      <a href="#sistema-lia" class="nav-link" on:click={handleAnchorClick}>Sistema LIA</a>
      <a href="#lia1" class="nav-link" on:click={handleAnchorClick}>LIA</a>
      <a href="#lia2" class="nav-link" on:click={handleAnchorClick}>Personal</a>
    </nav>
  </div>
</header>

<!-- Hero Section -->
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

<!-- How it works Section -->
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

<!-- LIA Section -->
<section class="section-lia" id="lia1">
  <div class="lia-container-left">
    <img class="lia-img-1" src="/assets/lia2.png" alt="LIA 2" />
    <img class="lia-img-2" src="/assets/lia1.png" alt="LIA 1" />
    <img class="lia-img-3" src="/assets/manuel.png" alt="Manuel" />
  </div>
  <div class="lia-container-right">
    <div class="lia-top">
      <h2>QUE OFRECE EL LIA</h2>
      <h3>Potencia tus estudios</h3>
      <p>El LIA ofrece acceso gratuito al uso de herramientas tecnológicas, que satisfacen las necesidades de docentes y estudiantes de la UNSJ, brindando oportunidades para mejorar la enseñanza y el aprendizaje.</p>
    </div>
    <div class="lia-bottom">
      <img src="/assets/pamela.png" alt="Pamela" />
      <img src="/assets/alumna.png" alt="Alumna" />
    </div>
  </div>
</section>

<!-- Cards Section -->
<section class="section-cards" id="lia2">
  <a href="#inicio" class="card-button" on:click={handleAnchorClick}>
    <h4>ESPACIOS DEL LIA</h4>
    <div class="card-icon-circle">
      <img src="/assets/arrow.svg" alt="Espacios" />
    </div>
    <div class="card-text-container">
      <p>Conoce los laboratorios</p>
      <img src="/assets/right-arrow.svg" alt="arrow" class="card-arrow" />
    </div>
  </a>
  <a href="#inicio" class="card-button" on:click={handleAnchorClick}>
    <h4>PERSONAL DEL LIA</h4>
    <div class="card-icon-circle">
      <img src="/assets/card.svg" alt="Personal" />
    </div>
    <div class="card-text-container">
      <p>Conoce al personal</p>
      <img src="/assets/right-arrow.svg" alt="arrow" class="card-arrow" />
    </div>
  </a>
  <a href="#inicio" class="card-button" on:click={handleAnchorClick}>
    <h4>ACTIVIDADES DEL LIA</h4>
    <div class="card-icon-circle">
      <img src="/assets/calendar.svg" alt="Actividades" />
    </div>
    <div class="card-text-container">
      <p>Conoce las actividades</p>
      <img src="/assets/right-arrow.svg" alt="arrow" class="card-arrow" />
    </div>
  </a>
</section>

<!-- Footer -->
<footer>
  <div class="footer-top">
    <div class="logo-container">
      <img src="/assets/app-logo.svg" alt="Logo" class="logo-icon" />
      <span class="logo-text" style="color: #aa8301;">Sistema LIA</span>
    </div>
    <div class="institucional">
      <img src="/assets/logo-lia.svg" alt="LIA" />
      <img src="/assets/logo-di.svg" alt="DI" />
      <img src="/assets/logo-exactas.svg" alt="Exactas" />
      <img src="/assets/logo-unsj.svg" alt="UNSJ" />
    </div>
  </div>
  <div class="footer-line"></div>
  <div class="footer-bottom">
    <p>2025 Sistema LIA. Todos los derechos reservados.</p>
    <p>Diseñado y Desarrollado por Martín Varela | 2025</p>
  </div>
</footer>
