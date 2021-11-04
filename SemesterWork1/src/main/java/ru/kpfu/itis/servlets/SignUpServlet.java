package ru.kpfu.itis.servlets;


import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private static final String MESS_USER_EXIST = "User with this email already exists";
    private SecurityService securityService;

    @Override
    public void init() {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = new Account(firstName, lastName, email, password);
        if(email != null) {
            try {
                if (securityService.isExist(email) != null) {
                    request.setAttribute("emailTip", MESS_USER_EXIST);
                } else if (securityService.isValidAccount(request, account)) {
                    this.securityService.signUp(account);
                    response.sendRedirect(request.getContextPath() + "/signIn");
                    return;
                }
            } catch (LoadingDataException ex){
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
            }
            request.setAttribute("password", password);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }
}
