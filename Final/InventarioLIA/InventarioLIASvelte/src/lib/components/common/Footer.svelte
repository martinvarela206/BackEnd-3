<script>
  import Logo from './Logo.svelte';
  export let dark = false;
</script>

<style>
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

  .logo-container {
    display: flex;
    align-items: center;
    gap: 12px;
  }

  .logo-text {
    font-size: 1.5rem;
    font-weight: bold;
    color: #dba800;
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
    .footer-top,
    .footer-bottom {
      flex-direction: column;
      text-align: center;
    }

    .institucional {
      margin: 0;
      flex-wrap: wrap;
      justify-content: center;
    }

    .institucional img {
      height: 48px;
    }
  }
</style>

<footer class:dark>
  <div class="footer-top">
    <div class="logo-container">
      <Logo variant="light" />
      <span class="logo-text">Sistema LIA</span>
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
