package com.martindev.inventariolia.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class ApiInfoResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() {
        return Response.ok("InventarioLia API activa").build();
    }
}
