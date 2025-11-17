<script>
  import Logo from '../common/Logo.svelte';
  export let handleAnchorClick;
</script>

<style>
  header {
    background: #fbc101;
    display: flex;
    justify-content: center;
    width: 100%;
    animation: fadeInTop ease-in 1s;
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

  @media (max-width: 768px) {
    .header-container {
      padding: 8px 20px;
    }

    .nav-links {
      gap: 16px;
      font-size: 0.9rem;
    }

    .logo-text {
      font-size: 1.2rem;
    }
  }
</style>

<header id="header">
  <div class="header-container">
    <div class="logo-container">
      <Logo variant="dark" />
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
