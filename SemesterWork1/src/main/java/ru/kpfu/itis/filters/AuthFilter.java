package ru.kpfu.itis.filters;

import ru.kpfu.itis.models.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private final String[] secretPages = {"/profile", "/search", "/subscribers", "/anotherProfile", "/logout",
            "/deleteAccount", "/subscribe", "/unsubscribe"};
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

        boolean isSecretPage = false;
        for(String page: secretPages){
            isSecretPage |= request.getRequestURI().equals(contextPath + page);
        }

        boolean isAuthenticated = false;
        boolean isStaticResource = request.getRequestURI().startsWith(contextPath +"/static/") ||
                request.getRequestURI().startsWith(contextPath +"/js/");

        if (session != null) {
            isAuthenticated = session.getAttribute("isAuthenticated") != null;
        }

        if (!isAuthenticated && isSecretPage && !isStaticResource) {
             response.sendRedirect(contextPath + "/signIn");
        } else if (isAuthenticated && !isSecretPage  && !isStaticResource) {
            Account account = (Account) session.getAttribute("account");
            response.sendRedirect(contextPath + "/profile?user=" + account.getId());
        } else {
             filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

