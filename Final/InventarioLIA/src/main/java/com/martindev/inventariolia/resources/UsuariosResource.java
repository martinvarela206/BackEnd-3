package com.martindev.inventariolia.resources;

import java.util.List;

import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosResource {
    @EJB
    private UsuariosFacade usuariosFacade;

    @GET
    public List<Usuarios> getAll() {
        return usuariosFacade.findAll();
    }

    @GET
    @Path("{id}")
    public Usuarios getById(@PathParam("id") Integer id) {
        return usuariosFacade.find(id);
    }

    @POST
    public Response create(Usuarios usuario) {
        usuariosFacade.create(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Usuarios usuario) {
        usuario.setId(id);
        usuariosFacade.edit(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Usuarios usuario = usuariosFacade.find(id);
        if (usuario != null) {
            usuariosFacade.remove(usuario);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
