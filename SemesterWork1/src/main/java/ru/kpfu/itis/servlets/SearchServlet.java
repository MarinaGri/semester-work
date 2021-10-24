package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Vacancy;
import ru.kpfu.itis.services.VacanciesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private final String[] params = {"experience", "employment", "schedule", "keyword",
            "salary", "experience", "employment", "schedule", "isWithSalary"};
    private VacanciesService vacanciesService;

    @Override
    public void init() {
        vacanciesService = (VacanciesService) getServletContext().getAttribute("vacanciesService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("experience", vacanciesService.getExperience());
        request.setAttribute("employment", vacanciesService.getEmployment());
        request.setAttribute("schedule", vacanciesService.getSchedule());
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String salary = request.getParameter("salary");
        String experience = request.getParameter("experience");
        String employment = request.getParameter("employment");
        String schedule = request.getParameter("schedule");
        String isWithSalary = request.getParameter("isWithSalary");
        Vacancy vacancy = new Vacancy(keyword, experience, employment,
                schedule, salary, isWithSalary != null && isWithSalary.equals("on")? "true": "false");

        request.setAttribute("vacancies", vacanciesService.getVacancies(vacancy));
//        for(String param: params){
//            request.setAttribute(param, request.getParameter(param));
//        }
        request.setAttribute("keyword", keyword);
        request.setAttribute("salary", salary);
        request.setAttribute("expVal", experience);
        request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
