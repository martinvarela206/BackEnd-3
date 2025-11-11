package com.inventariolia;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    // No necesitas agregar métodos aquí, JAX-RS detectará automáticamente los recursos
}
