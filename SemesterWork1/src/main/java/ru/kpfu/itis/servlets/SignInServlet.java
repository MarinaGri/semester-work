package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    public void init() {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = new Account(email, password);

        if (email != null) {
            if (securityService.isSignIn(account)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("isAuthenticated", true);
                session.setAttribute("account", securityService.isExist(email));
                response.sendRedirect(request.getContextPath() + "/profile");
                return;
            }
            request.setAttribute("passwordTip","Wrong email or password");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }
}
