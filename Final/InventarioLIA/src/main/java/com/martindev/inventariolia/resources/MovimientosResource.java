package com.martindev.inventariolia.resources;

import java.text.SimpleDateFormat;
import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.json.Json;
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

@Path("movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientosResource {
    @EJB
    private MovimientosFacade movimientosFacade;
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private UsuariosFacade usuariosFacade;

    @GET
    public Response getAll() {
        List<Movimientos> movimientos = movimientosFacade.findAll();
        var jsonArray = Json.createArrayBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
        
        for (Movimientos m : movimientos) {
            String userName = (m.getUserId() != null) ? m.getUserId().getNombre() : "";
            String nroLiaValue = (m.getNroLia() != null) ? m.getNroLia().getNroLia() : "";
            String fechaFormateada = (m.getFecha() != null) ? sdf.format(m.getFecha()) : "";
            
            jsonArray.add(Json.createObjectBuilder()
                .add("id", m.getId())
                .add("nroUnsj", m.getNroUnsj() != null ? m.getNroUnsj() : "")
                .add("estado", m.getEstado())
                .add("ubicacion", m.getUbicacion() != null ? m.getUbicacion() : "")
                .add("fecha", fechaFormateada)
                .add("comentario", m.getComentario() != null ? m.getComentario() : "")
                .add("nroLia", nroLiaValue)
                .add("userName", userName)
            );
        }
        
        return Response.ok(jsonArray.build()).build();
    }

    @GET
    @Path("elemento/{nroLia}")
    public Response getByElemento(@PathParam("nroLia") String nroLia) {
        Elementos elemento = elementosFacade.find(nroLia);
        if (elemento == null) {
            return Response.ok(Json.createArrayBuilder().build()).build();
        }
        
        List<Movimientos> movimientos = movimientosFacade.findByNroLia(elemento);
        var jsonArray = Json.createArrayBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
        
        for (Movimientos m : movimientos) {
            String userName = (m.getUserId() != null) ? m.getUserId().getNombre() : "";
            String nroLiaValue = (m.getNroLia() != null) ? m.getNroLia().getNroLia() : "";
            String fechaFormateada = (m.getFecha() != null) ? sdf.format(m.getFecha()) : "";
            
            jsonArray.add(Json.createObjectBuilder()
                .add("id", m.getId())
                .add("nroUnsj", m.getNroUnsj() != null ? m.getNroUnsj() : "")
                .add("estado", m.getEstado())
                .add("ubicacion", m.getUbicacion() != null ? m.getUbicacion() : "")
                .add("fecha", fechaFormateada)
                .add("comentario", m.getComentario() != null ? m.getComentario() : "")
                .add("nroLia", nroLiaValue)
                .add("userName", userName)
            );
        }
        
        return Response.ok(jsonArray.build()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) {
        Movimientos movimiento = movimientosFacade.find(id);
        if (movimiento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Construir JSON manualmente incluyendo nroLia y userId
        Integer userIdValue = 0;
        if (movimiento.getUserId() != null && movimiento.getUserId().getId() != null) {
            userIdValue = movimiento.getUserId().getId();
        }
        
        JsonObject json = Json.createObjectBuilder()
            .add("id", movimiento.getId())
            .add("nroUnsj", movimiento.getNroUnsj() != null ? movimiento.getNroUnsj() : "")
            .add("estado", movimiento.getEstado())
            .add("ubicacion", movimiento.getUbicacion() != null ? movimiento.getUbicacion() : "")
            .add("fecha", movimiento.getFecha().toString())
            .add("comentario", movimiento.getComentario() != null ? movimiento.getComentario() : "")
            .add("nroLia", movimiento.getNroLia() != null ? movimiento.getNroLia().getNroLia() : "")
            .add("userId", userIdValue)
            .build();
        
        return Response.ok(json).build();
    }

    @POST
    public Response create(JsonObject jsonData) {
        try {
            Movimientos movimiento = new Movimientos();
            
            // Establecer campos básicos
            if (jsonData.containsKey("nroUnsj")) {
                movimiento.setNroUnsj(jsonData.getString("nroUnsj", null));
            }
            if (jsonData.containsKey("estado")) {
                movimiento.setEstado(jsonData.getString("estado"));
            }
            if (jsonData.containsKey("ubicacion")) {
                movimiento.setUbicacion(jsonData.getString("ubicacion"));
            }
            if (jsonData.containsKey("comentario")) {
                movimiento.setComentario(jsonData.getString("comentario", null));
            }
            
            // Establecer fecha (automática)
            movimiento.setFecha(new java.util.Date());
            
            // Establecer nroLia
            if (jsonData.containsKey("nroLia")) {
                String nroLiaValue;
                if (jsonData.get("nroLia").getValueType() == jakarta.json.JsonValue.ValueType.OBJECT) {
                    nroLiaValue = jsonData.getJsonObject("nroLia").getString("nroLia");
                } else {
                    nroLiaValue = jsonData.getString("nroLia");
                }
                Elementos elemento = elementosFacade.find(nroLiaValue);
                if (elemento != null) {
                    movimiento.setNroLia(elemento);
                }
            }
            
            // Establecer userId
            if (jsonData.containsKey("userId")) {
                Integer userIdValue;
                if (jsonData.get("userId").getValueType() == jakarta.json.JsonValue.ValueType.OBJECT) {
                    userIdValue = jsonData.getJsonObject("userId").getInt("id");
                } else {
                    userIdValue = jsonData.getInt("userId");
                }
                Usuarios usuario = usuariosFacade.find(userIdValue);
                if (usuario != null) {
                    movimiento.setUserId(usuario);
                }
            }
            
            movimientosFacade.create(movimiento);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, JsonObject jsonData) {
        try {
            Movimientos movimiento = movimientosFacade.find(id);
            if (movimiento == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            // Actualizar campos básicos
            if (jsonData.containsKey("nroUnsj")) {
                movimiento.setNroUnsj(jsonData.getString("nroUnsj", null));
            }
            if (jsonData.containsKey("estado")) {
                movimiento.setEstado(jsonData.getString("estado"));
            }
            if (jsonData.containsKey("ubicacion")) {
                movimiento.setUbicacion(jsonData.getString("ubicacion"));
            }
            if (jsonData.containsKey("comentario")) {
                movimiento.setComentario(jsonData.getString("comentario", null));
            }
            
            // Actualizar nroLia si viene como objeto o como string
            if (jsonData.containsKey("nroLia")) {
                String nroLiaValue;
                if (jsonData.get("nroLia").getValueType() == jakarta.json.JsonValue.ValueType.OBJECT) {
                    nroLiaValue = jsonData.getJsonObject("nroLia").getString("nroLia");
                } else {
                    nroLiaValue = jsonData.getString("nroLia");
                }
                Elementos elemento = elementosFacade.find(nroLiaValue);
                if (elemento != null) {
                    movimiento.setNroLia(elemento);
                }
            }
            
            // Actualizar userId si viene como objeto o como número
            if (jsonData.containsKey("userId")) {
                Integer userIdValue;
                if (jsonData.get("userId").getValueType() == jakarta.json.JsonValue.ValueType.OBJECT) {
                    userIdValue = jsonData.getJsonObject("userId").getInt("id");
                } else {
                    userIdValue = jsonData.getInt("userId");
                }
                Usuarios usuario = usuariosFacade.find(userIdValue);
                if (usuario != null) {
                    movimiento.setUserId(usuario);
                }
            }
            
            movimientosFacade.edit(movimiento);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Movimientos movimiento = movimientosFacade.find(id);
        if (movimiento != null) {
            movimientosFacade.remove(movimiento);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
