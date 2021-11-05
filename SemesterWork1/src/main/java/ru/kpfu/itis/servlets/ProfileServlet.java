package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Post;
import ru.kpfu.itis.services.PublicationService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.services.SubscribersService;
import ru.kpfu.itis.services.VacanciesService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private PublicationService publicationService;
    private SubscribersService subscribersService;

    @Override
    public void init() {
        this.subscribersService = (SubscribersService) getServletContext().getAttribute("subscribersService");
        this.publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isOwnProfile(request, response)) {
            HttpSession session = request.getSession(false);
            Account account = (Account) session.getAttribute("account");
            setProfileData(request, response, account);
            request.getRequestDispatcher("WEB-INF/jsp/posts.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/anotherProfile?user=" + request.getParameter("user"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isOwnProfile(request, response)){
            getPageForOwnProfile(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/anotherProfile?user=" + request.getParameter("user"));
        }
    }

    private boolean isOwnProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strId = request.getParameter("user");
        if(strId == null){
            return true;
        }
        Integer id = null;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException ex){
            response.setStatus(404);
            ShowErrorHelper.showErrorMessage(request, response,
                    "User's page with this id doesn't exist", "error");
        }

        Account accountFromReq = null;
        try {
            accountFromReq = subscribersService.findById(id);
        } catch (LoadingDataException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
        }
        HttpSession session = request.getSession(false);
        Account accountFromSession = (Account) session.getAttribute("account");
        if(accountFromReq == null) {
            ShowErrorHelper.showErrorMessage(request, response,
                    "User's page with this id doesn't exist", "error");
        }
        return accountFromReq.getId().equals(accountFromSession.getId());
    }

    private void getPageForOwnProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String title = request.getParameter("title");
        String text = request.getParameter("post-text");
        Post post = new Post(title, text, account.getId());

        if(request.getParameter("saveComment") != null){
            Integer id = Integer.parseInt(request.getParameter("saveComment"));
            Comment comment = new Comment(request.getParameter("comment"), account.getId(),null, id);
            try {
                publicationService.postComment(comment);
                response.sendRedirect(request.getContextPath() + "/profile?user=" + account.getId());
                return;
            } catch (SavingFailedException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
            }
        }

        editPost(request, response, post, account);
    }

    private  void setProfileData(HttpServletRequest request, HttpServletResponse response,
                                 Account account) throws ServletException, IOException {
        request.setAttribute("account", account);
        try {
            request.setAttribute("numOfPosts", publicationService.getNumOfPosts(account.getId()));
            List<Post> posts = publicationService.findPosts(account.getId());
            for(int i = 0; i < posts.size(); i++){
                List<Comment> commentList = publicationService.getCommentsByPostId(posts.get(i).getId());
                if(commentList != null) {
                    posts.get(i).setComments(commentList);
                }
            }
            request.setAttribute("posts", posts);
        } catch (LoadingDataException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "profile");
        }
        try {
            request.setAttribute("numOfSubs", subscribersService.getNumOfSubs(account.getId()));
        } catch (LoadingDataException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "profile");
        }
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response, Post post, Account account) throws IOException, ServletException {
        if(request.getParameter("edit") != null) {
            request.setAttribute("edit", request.getParameter("postEdit"));
        } else
            try {
                if (request.getParameter("save") != null) {
                    Integer id = Integer.valueOf(request.getParameter("postEdit"));
                    post.setId(id);
                    publicationService.updatePost(post);
                    request.setAttribute("edit", null);
                    response.sendRedirect(request.getContextPath() + "/profile?user=" + account.getId());
                    return;
                } else if (request.getParameter("delete") != null) {
                    Integer id = Integer.valueOf(request.getParameter("postEdit"));
                    post.setId(id);
                    publicationService.deletePost(post);
                } else if (request.getParameter("post") != null) {
                    publicationService.savePost(post);
                    setProfileData(request, response, account);
                    response.sendRedirect(request.getContextPath() + "/profile?user=" + account.getId());
                    return;
                }
            } catch (RemovalFailedException | LoadingDataException | SavingFailedException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "profile");
            }
        doGet(request, response);
    }
}

