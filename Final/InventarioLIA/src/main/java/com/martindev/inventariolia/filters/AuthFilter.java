package com.martindev.inventariolia.filters;

import java.io.IOException;
import java.util.List;

import com.martindev.inventariolia.Roles;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();


        // Permitir acceso libre a la API REST (Svelte)
        if (uri.contains("/api/")) {
            chain.doFilter(req, res);
            return;
        }

        // Permitir acceso libre a login y recursos estáticos
        if (uri.endsWith("login") || uri.endsWith("login.jsp") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")) {
            chain.doFilter(req, res);
            return;
        }

        // Verificar autenticación
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Verificar autorización por roles
        @SuppressWarnings("unchecked")
        List<Roles> roles = (List<Roles>) session.getAttribute("roles");
        boolean autorizado = false;

        // Lógica de autorización por URL y rol
        if (uri.contains("elementos") || uri.contains("movimientos")) {
            if (tieneRol(roles, "user_admin") || tieneRol(roles, "coordinador")) {
                autorizado = true; // CRUD completo
            } else if (tieneRol(roles, "tecnico")) {
                // Solo permitir añadir (POST con accion=crear)
                String accion = request.getParameter("accion");
                if ("crear".equals(accion) && "POST".equals(request.getMethod())) {
                    autorizado = true;
                } else if ("GET".equals(request.getMethod())) {
                    autorizado = true; // Puede ver listados
                }
            } else if (tieneRol(roles, "revisor")) {
                if ("GET".equals(request.getMethod())) {
                    autorizado = true; // Solo ver listados
                }
            }
        } else {
            autorizado = true; // Otras páginas públicas
        }

        if (!autorizado) {
            session.setAttribute("alerta", "No tiene permisos necesarios");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        chain.doFilter(req, res);
    }

    private boolean tieneRol(List<Roles> roles, String rol) {
        for (Roles r : roles) {
            if (r.getRol().equals(rol)) return true;
        }
        return false;
    }
}