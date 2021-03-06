package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.PublicationService;
import ru.kpfu.itis.services.SubscribersService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscribers")
public class SubscribersServlet extends HttpServlet {
    private SubscribersService subscribersService;
    private PublicationService publicationService;


    @Override
    public void init() throws ServletException {
        this.subscribersService = (SubscribersService) getServletContext().getAttribute("subscribersService");
        this.publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = null;
        try {
            Integer id = Integer.valueOf(request.getParameter("user"));
            account = subscribersService.findById(id);
        } catch (NumberFormatException|LoadingDataException e) {
            response.setStatus(404);
            ShowErrorHelper.showErrorMessage(request, response,
                    "User's page with this id doesn't exist", "error");
        }
        if(account != null) {
            request.setAttribute("account", account);
            try {
                request.setAttribute("numOfPosts", publicationService.getNumOfPosts(account.getId()));
                request.setAttribute("posts", publicationService.findPosts(account.getId()));
            } catch (LoadingDataException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "subscribers");
            }
            try {
                request.setAttribute("numOfSubs", subscribersService.getNumOfSubs(account.getId()));
                request.setAttribute("accounts", subscribersService.findSubs(account.getId()));
            } catch (LoadingDataException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "subscribers");
            }
        }
        request.getRequestDispatcher("WEB-INF/jsp/subscribers.jsp").forward(request, response);
    }
}
