package ru.kpfu.itis.servlets;

import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Vacancy;
import ru.kpfu.itis.services.PublicationService;
import ru.kpfu.itis.services.SecurityService;
import ru.kpfu.itis.services.VacanciesService;
import ru.kpfu.itis.utils.ShowErrorHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private final String[] params = {"keyword", "salary", "experience", "employment", "schedule", "onlyWithSalary"};
    private VacanciesService vacanciesService;
    private PublicationService publicationService;
    private SecurityService securityService;

    @Override
    public void init() {
        vacanciesService = (VacanciesService) getServletContext().getAttribute("vacanciesService");
        publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vacancy vacancy = getVacancy(request);

        if(vacancy != null) {
            try {
                setVacancies(request, vacancy);
            } catch (LoadingDataException ex) {
                ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "search");
            }
        }

        try {
            setDictionaries(request);
        } catch (LoadingDataException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "error");
        }

        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String text = request.getParameter("comment");
        Integer numVac = request.getParameter("numVac") == null? null: Integer.parseInt(request.getParameter("numVac"));
        Comment comment = new Comment(text, account.getId(), numVac,null);

        try {
            if (request.getParameter("save") != null) {
                Integer id = Integer.valueOf(request.getParameter("save"));
                comment.setId(id);
                publicationService.updateComment(comment);
            }

            if (request.getParameter("post") != null) {
                session.setAttribute("comment", comment.getNumVacancy());
                publicationService.postComment(comment);
                postComment(request);
            }
            if (request.getParameter("delete") != null){
                Integer id = Integer.valueOf(request.getParameter("delete"));
                comment.setId(id);
                publicationService.deleteComment(comment);
            }

        } catch (LoadingDataException | RemovalFailedException | SavingFailedException ex) {
            ShowErrorHelper.showErrorMessage(request, response, ex.getMessage(), "search");
        }
        doGet(request, response);
    }

    private void setDictionaries(HttpServletRequest request) throws LoadingDataException {
        request.setAttribute("experience", vacanciesService.getExperience());
        request.setAttribute("employment", vacanciesService.getEmployment());
        request.setAttribute("schedule", vacanciesService.getSchedule());
    }

    private Vacancy getVacancyFromRequest(HttpServletRequest request){
        if(securityService.isValidSearchData(request,
                request.getParameter("keyword"), request.getParameter("salary"))) {
            String[] attributes = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                attributes[i] = request.getParameter(params[i]);
            }
            int i = 0;
            Vacancy vacancy = new Vacancy(attributes[i++], attributes[i++], attributes[i++],
                    attributes[i++], attributes[i++], attributes[i] != null && attributes[i].equals("on"));

            return vacancy;
        }
        return null;
    }

    private void setAttributesFromSession(HttpServletRequest request, HttpSession session){
        Vacancy vacancy = (Vacancy) session.getAttribute("vacancy");
        if(vacancy != null){
            request.setAttribute("keywordVal", vacancy.getText());
            request.setAttribute("salaryVal", vacancy.getSalary());
            request.setAttribute("experienceVal", vacancy.getExperience());
            request.setAttribute("employmentVal", vacancy.getEmployment());
            request.setAttribute("scheduleVal", vacancy.getSchedule());
            request.setAttribute("onlyWithSalaryVal", vacancy.getOnlyWithSalary());
        }
    }

    private void setComments(List<Vacancy> vacancies) throws LoadingDataException {
        for(int i = 0; i < vacancies.size(); i++){
            List<Comment> commentList = publicationService.getCommentsByNumVacancy(vacancies.get(i).getNumber());
            if(commentList != null) {
                vacancies.get(i).setComments(commentList);
            }
        }
    }

    private void setVacancies(HttpServletRequest request, Vacancy vacancy) throws LoadingDataException {
        List<Vacancy> vacancies = vacanciesService.getVacancies(vacancy);
        setComments(vacancies);
        request.setAttribute("vacancies", vacancies);
    }

    private void postComment(HttpServletRequest request) throws SavingFailedException {
        String numStr = request.getParameter("numVacancy");
        if(numStr != null) {
            HttpSession session = request.getSession(false);
            Account account = (Account) session.getAttribute("account");
            Comment comment = new Comment(request.getParameter("comment"), account.getId(), Integer.parseInt(numStr), null);
            publicationService.postComment(comment);
        }
    }

    private Vacancy getVacancy(HttpServletRequest request){
        Vacancy vacancy = null;
        HttpSession session = request.getSession(false);
        if(request.getParameter("sent") != null){
            vacancy = getVacancyFromRequest(request);
            session.setAttribute("vacancy", vacancy);
            for(String param: params){
                request.setAttribute(param + "Val", request.getParameter(param));
            }
        } else {
            if(session.getAttribute("vacancy") != null) {
                vacancy = (Vacancy) session.getAttribute("vacancy");
            }
            else if(session.getAttribute("comment") != null){
                vacancy = (Vacancy) session.getAttribute("comment");
            }
            setAttributesFromSession(request, session);
        }
        return vacancy;
    }
}
