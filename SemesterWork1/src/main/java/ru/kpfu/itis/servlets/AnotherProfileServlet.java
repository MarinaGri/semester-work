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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/anotherProfile")
public class AnotherProfileServlet extends HttpServlet {
    private SubscribersService subscribersService;
    private PublicationService publicationService;

    @Override
    public void init() {
        this.subscribersService = (SubscribersService) getServletContext().getAttribute("subscribersService");
        this.publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = null;
        Integer id = null;
        try {
            id = Integer.valueOf(request.getParameter("user"));
            account = subscribersService.findById(id);
        } catch (NumberFormatException|LoadingDataException ex){
            ShowErrorHelper.showErrorMessage(request, response,
                    "User's page with this id doesn't exist", "error");
            return;
        }
        if(account != null) {
            HttpSession session = request.getSession(false);
            Account ownAcc = (Account) session.getAttribute("account");
            request.setAttribute("account", account);

            String isSubscribe = null;
            try {
                isSubscribe = subscribersService.isSubscriber(ownAcc.getId(), account.getId())? "Отписаться": "Подписаться";
            } catch (LoadingDataException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "anotherProfile");
            }
            request.setAttribute("isSubscribe", isSubscribe);
            try {
                request.setAttribute("numOfPosts", publicationService.getNumOfPosts(account.getId()));
                request.setAttribute("numOfSubs", subscribersService.getNumOfSubs(account.getId()));
                request.setAttribute("posts", publicationService.findPosts(account.getId()));
            } catch (LoadingDataException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "anotherProfile");
            }
        }
        request.getRequestDispatcher("WEB-INF/jsp/anotherProfile.jsp").forward(request, response);
    }
}
