package com.martindev.inventariolia.controllers;

import java.io.IOException;
import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detalleelemento")
public class DetalleElementoServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nroLia = request.getParameter("nroLia");
        Elementos elemento = elementosFacade.find(nroLia);
        List<Movimientos> movimientos = movimientosFacade.findByNroLia(elemento);
        request.setAttribute("elemento", elemento);
        request.setAttribute("movimientos", movimientos);
        request.getRequestDispatcher("detalleelemento.jsp").forward(request, response);
    }
}