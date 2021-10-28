package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Vacancy;
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
        setDictionaries(request);
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vacancy vacancy = getVacancy(request);

        postComment(request);

        if(vacancy != null) {
            setVacancies(request, vacancy);
        }

        setDictionaries(request);
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }

    private void setDictionaries(HttpServletRequest request){
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

    private void setComments(List<Vacancy> vacancies){
        for(int i = 0; i < vacancies.size(); i++){
            List<Comment> commentList = publicationService.getCommentsByNumVacancy(vacancies.get(i).getNumber());
            if(commentList != null) {
                vacancies.get(i).setComments(commentList);
            }
        }
    }

    private void setVacancies(HttpServletRequest request, Vacancy vacancy){
        List<Vacancy> vacancies = vacanciesService.getVacancies(vacancy);
        setComments(vacancies);
        request.setAttribute("vacancies", vacancies);
    }

    private void postComment(HttpServletRequest request){
        String numStr = request.getParameter("numVacancy");
        if(numStr != null) {
            Comment comment = new Comment(request.getParameter("comment"), Integer.parseInt(numStr));
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
            setAttributesFromSession(request, session);
        }
        return vacancy;
    }
}
