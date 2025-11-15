package com.martindev.inventariolia.controllers;

import java.io.IOException;
import java.util.List;

import com.martindev.inventariolia.Roles;
import com.martindev.inventariolia.Usuarios;
import com.martindev.inventariolia.UsuariosFacade;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @EJB
    private UsuariosFacade usuariosFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        Usuarios usuario = usuariosFacade.findByNombreYPassword(nombre, password);
        if (usuario != null) {
            List<Roles> roles = usuariosFacade.obtenerRoles(usuario);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("roles", roles);
            for (Roles r : roles) {
                System.out.println("ROL EN SESION: " + r.getRol());
            }
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}