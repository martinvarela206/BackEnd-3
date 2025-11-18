package com.martindev.inventariolia.controllers;

import java.io.IOException;
import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/elementos")
public class ElementosServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;
    

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
            // Obtener usuario de sesión y pasar al Facade para asociar el movimiento
            jakarta.servlet.http.HttpSession session = request.getSession(false);
            com.martindev.inventariolia.Usuarios usuarioEnSesion = null;
            if (session != null) {
                Object uobj = session.getAttribute("usuario");
                if (uobj instanceof com.martindev.inventariolia.Usuarios) {
                    usuarioEnSesion = (com.martindev.inventariolia.Usuarios) uobj;
                }
            }
            if (usuarioEnSesion == null) {
                // Requerir login: si no hay usuario en sesión redirigimos al login
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            elementosFacade.create(e, usuarioEnSesion);
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