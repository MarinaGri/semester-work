package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.OAuthService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.utils.DbWrapper;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SecurityService securityService;
    private OAuthService oAuthService;

    @Override
    public void init() {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        oAuthService = (OAuthService) getServletContext().getAttribute("oAuthService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties environment = new Properties();
        environment.load(DbWrapper.class.getResourceAsStream("/application.properties"));
        request.setAttribute("clientId", environment.getProperty("client.id"));
        String token = request.getParameter("access_token");
        if(token != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("isAuthenticated", true);
            Account account = null;
            try {
                account = oAuthService.isExist(token);
                if(account == null) {
                    account = oAuthService.getAccountData(token);
                    oAuthService.signInWithToken(account);
                }
            } catch (LoadingDataException|SavingFailedException|ConnectionLostException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
            }
            session.setAttribute("account", account);
            response.sendRedirect(request.getContextPath() + "/profile?user=" + account.getId());
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = new Account(email, password);

        if (email != null) {
            try {
                if (securityService.isSignIn(account)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("isAuthenticated", true);
                    Account acc = securityService.isExist(email);
                    session.setAttribute("account", acc);
                    response.sendRedirect(request.getContextPath() + "/profile?user=" + acc.getId());
                    return;
                }
            } catch (LoadingDataException ex){
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
            }
            request.setAttribute("passwordTip","Wrong email or password");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }
}
