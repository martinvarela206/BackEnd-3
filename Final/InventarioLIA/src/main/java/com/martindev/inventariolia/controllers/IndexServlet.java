package com.martindev.inventariolia.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Elementos> elementos = elementosFacade.findAll();
        Map<String, String> ubicaciones = new HashMap<>();
        for (Elementos e : elementos) {
            List<Movimientos> movs = movimientosFacade.findByNroLia(e);
            if (movs != null && !movs.isEmpty()) {
                Movimientos ultimo = movs.get(0); // el más reciente
                ubicaciones.put(e.getNroLia(), ultimo.getUbicacion());
            }
        }
        request.setAttribute("elementos", elementos);
        request.setAttribute("ubicaciones", ubicaciones);
        request.getRequestDispatcher("index.jsp").forward(request, response); // forward sigue igual, solo los redirect cambian
    }
}
