package com.martindev.inventariolia.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;
import com.martindev.inventariolia.Usuarios;

@WebServlet("/elementos")
public class ElementosServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("editar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos elemento = elementosFacade.find(nroLia);
            request.setAttribute("elemento", elemento);
            request.getRequestDispatcher("editarelemento.jsp").forward(request, response);
        } else {
            List<Elementos> lista = elementosFacade.findAll();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("elementos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("crear".equals(accion)) {
            Elementos e = new Elementos();
            e.setNroLia(request.getParameter("nroLia"));
            e.setNroUnsj(request.getParameter("nroUnsj"));
            e.setTipo(request.getParameter("tipo"));
            e.setDescripcion(request.getParameter("descripcion"));
            e.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            elementosFacade.create(e);
            
            // Crearle el movimiento inicial por defecto
            Movimientos m = new Movimientos();
            m.setNroLia(e);
            m.setNroUnsj(e.getNroUnsj());
            m.setEstado("ingresado");
            m.setUbicacion("Lab FB"); // Puedes ajustar la ubicación por defecto
            m.setFecha(new java.util.Date());
            m.setComentario("Ingreso inicial");
            
            // Asociarlo al usuario, por defecto sera admin:
            Usuarios usuario = new Usuarios();
            usuario.setId(1); // El id del usuario admin creado en tu script SQL
            m.setUserId(usuario);
            
            movimientosFacade.create(m);
            
            response.sendRedirect("elementos");
        } else if ("modificar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos e = elementosFacade.find(nroLia);
            if (e != null) {
                e.setNroUnsj(request.getParameter("nroUnsj"));
                e.setTipo(request.getParameter("tipo"));
                e.setDescripcion(request.getParameter("descripcion"));
                e.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                elementosFacade.edit(e);
            }
            response.sendRedirect("elementos");
        } else if ("eliminar".equals(accion)) {
            String nroLia = request.getParameter("nroLia");
            Elementos e = elementosFacade.find(nroLia);
            if (e != null) {
                elementosFacade.remove(e);
            }
            response.sendRedirect("elementos");
        }
    }
}