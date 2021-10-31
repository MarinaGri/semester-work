package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.SubscribersService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/unsubscribe")
public class UnsubscribeServlet extends HttpServlet {

    private SubscribersService subscribersService;


    @Override
    public void init() throws ServletException {
        this.subscribersService = (SubscribersService) getServletContext().getAttribute("subscribersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("user"));
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");
        try {
            subscribersService.unsubscribe(account.getId(), id);
        } catch (RemovalFailedException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "/anotherProfile?user=" + id);
        }
        response.sendRedirect(request.getContextPath() + "/anotherProfile?user=" + id);
    }
}
