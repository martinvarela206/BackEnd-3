package com.inventariolia.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// Define la ruta del recurso, las anotaciones Path son anidables.
@Path("/hello")
public class HelloTest {

    // Endpoint que devuelve texto plano
    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloText() {
        return "Hello, World!";
    }

    // Endpoint que devuelve JSON
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHelloJson() {
        return "{\"message\": \"Hello, World!\"}";
    }
}