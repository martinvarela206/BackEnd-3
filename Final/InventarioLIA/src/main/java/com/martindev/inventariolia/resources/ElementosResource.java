package com.martindev.inventariolia.resources;

import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.json.JsonObject;
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


@Path("elementos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementosResource {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;
    @EJB
    private UsuariosFacade usuariosFacade;
    @GET
    @Path("{nroLia}/ultima-ubicacion")
    public Response getUltimaUbicacion(@PathParam("nroLia") String nroLia) {
        Elementos elemento = elementosFacade.find(nroLia);
        String ubicacion = "-";
        if (elemento != null) {
            List<Movimientos> movs = movimientosFacade.findByNroLia(elemento);
            if (movs != null && !movs.isEmpty()) {
                Movimientos ultimo = movs.get(0); // ya está ordenado por fecha desc
                if (ultimo.getUbicacion() != null) {
                    ubicacion = ultimo.getUbicacion();
                }
            }
        }
        return Response.ok(java.util.Collections.singletonMap("ubicacion", ubicacion)).build();
    }

    @GET
    public List<Elementos> getAll() {
        return elementosFacade.findAll();
    }

    @GET
    @Path("{id}")
    public Elementos getById(@PathParam("id") String id) {
        return elementosFacade.find(id);
    }

    @POST
    public Response create(JsonObject jsonData) {
        try {
            Elementos e = new Elementos();

            if (jsonData.containsKey("nroLia")) {
                e.setNroLia(jsonData.getString("nroLia"));
            }
            if (jsonData.containsKey("nroUnsj")) {
                e.setNroUnsj(jsonData.getString("nroUnsj", null));
            }
            if (jsonData.containsKey("tipo")) {
                e.setTipo(jsonData.getString("tipo"));
            }
            if (jsonData.containsKey("descripcion")) {
                e.setDescripcion(jsonData.getString("descripcion", null));
            }
            if (jsonData.containsKey("cantidad")) {
                try {
                    e.setCantidad(jsonData.getInt("cantidad"));
                } catch (Exception ex) {
                    // intentar parsear como string
                    try {
                        e.setCantidad(Integer.valueOf(jsonData.getString("cantidad")));
                    } catch (Exception ex2) {
                        // dejar nulo
                    }
                }
            }

            Usuarios usuario = null;
            if (jsonData.containsKey("userId")) {
                try {
                    int userId = jsonData.getInt("userId");
                    usuario = usuariosFacade.find(userId);
                } catch (NumberFormatException nfe) {
                    // si viene como objeto, intentar obtener id dentro del objeto
                    try {
                        if (jsonData.get("userId").getValueType() == jakarta.json.JsonValue.ValueType.OBJECT) {
                            usuario = usuariosFacade.find(jsonData.getJsonObject("userId").getInt("id"));
                        } else {
                            usuario = usuariosFacade.find(Integer.parseInt(jsonData.getString("userId")));
                        }
                    } catch (Exception ex2) {
                        usuario = null;
                    }
                }
            }

            if (usuario == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("userId is required to create an element and its initial movement").build();
            }
            elementosFacade.create(e, usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, Elementos elemento) {
        elemento.setNroLia(id);
        elementosFacade.edit(elemento);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        Elementos elemento = elementosFacade.find(id);
        if (elemento != null) {
            elementosFacade.remove(elemento);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
