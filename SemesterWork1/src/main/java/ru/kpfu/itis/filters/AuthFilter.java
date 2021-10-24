package ru.kpfu.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        String contextPath = request.getContextPath();

        boolean isSecretPage = request.getRequestURI().equals(contextPath + "/profile");
        boolean isAuthenticated = false;

        if (session != null) {
            isAuthenticated = session.getAttribute("isAuthenticated") != null;
        }
        if (isAuthenticated && isSecretPage || !isAuthenticated && !isSecretPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            response.sendRedirect(contextPath + "/profile");
        } else {
            response.sendRedirect(contextPath + "/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}

