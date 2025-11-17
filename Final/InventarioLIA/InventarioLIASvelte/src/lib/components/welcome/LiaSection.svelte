<style>
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
</style>

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
