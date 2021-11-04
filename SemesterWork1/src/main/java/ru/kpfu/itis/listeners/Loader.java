package ru.kpfu.itis.listeners;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kpfu.itis.repositories.*;
import ru.kpfu.itis.services.*;
import ru.kpfu.itis.validators.InputValidatorRegex;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Loader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("securityService", new SecurityService(
                new AccountsRepositoryJdbcImpl(), new InputValidatorRegex(), new BCryptPasswordEncoder()));

        sce.getServletContext().setAttribute("inputValidator", new InputValidatorRegex());

        sce.getServletContext().setAttribute("vacanciesService",
                new VacanciesService(new VacanciesRepositoryAPIImpl(), new DictionariesRepositoryAPIImpl()));

        sce.getServletContext().setAttribute("publicationService",
                new PublicationService(new CommentsRepositoryJdbcImpl(), new PostsRepositoryJdbcImpl()));

        sce.getServletContext().setAttribute("subscribersService",
                new SubscribersService(new AccountsRepositoryJdbcImpl()));

        sce.getServletContext().setAttribute("oAuthService",
                new OAuthService(new AccountsRepositoryJdbcImpl()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
