package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.models.Post;
import ru.kpfu.itis.services.PublicationService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.services.VacanciesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private PublicationService publicationService;

    @Override
    public void init() {
        publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");
        request.setAttribute("firstName", account.getFirstName());
        request.setAttribute("lastName", account.getLastName());
        request.setAttribute("numOfPosts", publicationService.getNumOfPosts(account.getId()));
        request.setAttribute("posts", publicationService.findPosts(account.getId()));
        System.out.println(publicationService.findPosts(account.getId()));
        request.getRequestDispatcher("WEB-INF/jsp/posts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");
        String title = request.getParameter("title");
        String text = request.getParameter("post-text");
        Post post = new Post(title, text, account.getId());

        if(request.getParameter("edit") != null) {
            request.setAttribute("edit", request.getParameter("postEdit"));
        }
        if (request.getParameter("save") != null) {
                Integer id = Integer.valueOf(request.getParameter("postEdit"));
                post.setId(id);
                publicationService.updatePost(post);
                request.setAttribute("edit", null);
        }
        if(request.getParameter("delete") != null){
            Integer id = Integer.valueOf(request.getParameter("postEdit"));
            post.setId(id);
            publicationService.deletePost(post);
        }
        if(request.getParameter("post") != null){
            publicationService.savePost(post);
        }
        doGet(request, response);
    }
}

