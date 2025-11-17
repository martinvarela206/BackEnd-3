<style>
  .welcome-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    font-family: system-ui, sans-serif;
  }
  
  .welcome-card {
    background: white;
    border-radius: 20px;
    padding: 60px 80px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
    text-align: center;
    animation: fadeIn 0.8s ease-in;
  }
  
  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(-20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
  
  h1 {
    font-size: 3rem;
    color: #1976d2;
    margin: 0 0 20px 0;
    font-weight: 700;
  }
  
  p {
    font-size: 1.2rem;
    color: #666;
    margin: 0 0 40px 0;
  }
  
  .btn-inicio {
    display: inline-block;
    background: #1976d2;
    color: white;
    text-decoration: none;
    padding: 15px 40px;
    border-radius: 8px;
    font-size: 1.1rem;
    font-weight: 600;
    transition: all 0.3s ease;
    box-shadow: 0 4px 15px rgba(25, 118, 210, 0.3);
  }
  
  .btn-inicio:hover {
    background: #125ea2;
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(25, 118, 210, 0.4);
  }
</style>

<div class="welcome-container">
  <div class="welcome-card">
    <h1>Inventario LIA</h1>
    <p>Sistema de Gestión de Inventario</p>
    <a href="#/inicio" class="btn-inicio">Ingresar al Sistema</a>
  </div>
</div>
