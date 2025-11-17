package com.martindev.inventariolia.controllers;

import java.io.IOException;
import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/movimientos")
public class MovimientosServlet extends HttpServlet {
    @EJB
    private MovimientosFacade movimientosFacade;
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private UsuariosFacade usuariosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("nuevo".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            List<Elementos> elementos = elementosFacade.findAll();
            List<Usuarios> usuarios = usuariosFacade.findAll();
            request.setAttribute("elementos", elementos);
            request.setAttribute("usuarios", usuarios);
            if (nroLia != null && !nroLia.isEmpty()) {
                request.setAttribute("nroLiaSeleccionado", nroLia);
            }
            request.getRequestDispatcher("nuevomovimiento.jsp").forward(request, response);
        } else if ("editar".equals(accion)) {
            String id = request.getParameter("id");
            Movimientos movimiento = movimientosFacade.find(Integer.valueOf(id));
            List<Elementos> elementos = elementosFacade.findAll();
            List<Usuarios> usuarios = usuariosFacade.findAll();
            request.setAttribute("movimiento", movimiento);
            request.setAttribute("elementos", elementos);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("editarmovimiento.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Movimientos m = movimientosFacade.find(id);
            if (m != null) {
                movimientosFacade.remove(m);
            }
            response.sendRedirect("movimientos");
        } else {
            List<Movimientos> lista = movimientosFacade.findAll();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("movimientos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("crear".equals(accion)) {
            Movimientos m = new Movimientos();
            String nroLiaParam = request.getParameter("nroLia");
            Elementos elemento = elementosFacade.find(nroLiaParam);
            m.setNroLia(elemento);
            m.setNroUnsj(request.getParameter("nroUnsj"));
            m.setEstado(request.getParameter("estado"));
            m.setUbicacion(request.getParameter("ubicacion"));
            m.setComentario(request.getParameter("comentario"));
            m.setFecha(new java.util.Date());
            int userId = Integer.parseInt(request.getParameter("userId"));
            Usuarios usuario = usuariosFacade.find(userId);
            m.setUserId(usuario);
            movimientosFacade.create(m);
            response.sendRedirect("movimientos");
        } else if ("modificar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Movimientos m = movimientosFacade.find(id);
            if (m != null) {
                String nroLiaParam = request.getParameter("nroLia");
                Elementos elemento = elementosFacade.find(nroLiaParam);
                m.setNroLia(elemento);
                m.setNroUnsj(request.getParameter("nroUnsj"));
                m.setEstado(request.getParameter("estado"));
                m.setUbicacion(request.getParameter("ubicacion"));
                m.setComentario(request.getParameter("comentario"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                Usuarios usuario = usuariosFacade.find(userId);
                m.setUserId(usuario);
                movimientosFacade.edit(m);
            }
            response.sendRedirect("movimientos");
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Movimientos m = movimientosFacade.find(id);
            if (m != null) {
                movimientosFacade.remove(m);
            }
            response.sendRedirect("movimientos");
        }
    }
}
