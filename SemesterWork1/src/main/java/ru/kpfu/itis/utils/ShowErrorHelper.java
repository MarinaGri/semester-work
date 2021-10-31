package ru.kpfu.itis.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorHelper {

    public static void showErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                        String message, String page) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp").forward(request, response);
    }
}
