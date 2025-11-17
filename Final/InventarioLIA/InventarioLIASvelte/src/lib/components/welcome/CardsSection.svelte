<script>
  export let handleAnchorClick;
</script>

<style>
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
</style>

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
