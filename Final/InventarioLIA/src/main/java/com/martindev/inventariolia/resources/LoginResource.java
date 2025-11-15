package com.martindev.inventariolia.resources;

import java.util.List;
import java.util.Map;

import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    @EJB
    private UsuariosFacade usuariosFacade;

    @POST
    public Response login(Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        List<Usuarios> usuarios = usuariosFacade.findAll();
        for (Usuarios u : usuarios) {
            if (u.getNombre().equals(username) && u.getPassword().equals(password)) {
                return Response.ok(u).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
    }
}
