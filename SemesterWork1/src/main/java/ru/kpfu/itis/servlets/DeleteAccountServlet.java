package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    public void init() {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");
        session.removeAttribute("isAuthenticated");
        session.removeAttribute("account");
        try {
            securityService.deleteAccount(account);
        } catch (RemovalFailedException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
        }
        response.sendRedirect(request.getContextPath() + "/signUp");
    }
}
