package ru.kpfu.itis.listeners;

import ru.kpfu.itis.repositories.AccountsRepositoryJdbcImpl;
import ru.kpfu.itis.repositories.DictionariesRepositoryAPIImpl;
import ru.kpfu.itis.repositories.VacanciesRepositoryAPIImpl;
import ru.kpfu.itis.services.SecurityServiceImpl;
import ru.kpfu.itis.services.VacanciesService;
import ru.kpfu.itis.validators.InputValidatorRegex;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Loader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("securityService",
                new SecurityServiceImpl(new AccountsRepositoryJdbcImpl(), new InputValidatorRegex()));
        sce.getServletContext().setAttribute("inputValidator", new InputValidatorRegex());
        sce.getServletContext().setAttribute("vacanciesService",
                new VacanciesService(new VacanciesRepositoryAPIImpl(), new DictionariesRepositoryAPIImpl()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
