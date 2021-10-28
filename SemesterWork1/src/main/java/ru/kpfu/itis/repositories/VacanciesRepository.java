package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.exceptions.WrongVacancyParamException;
import ru.kpfu.itis.models.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacanciesRepository {
    Optional<List<Vacancy>> getVacanciesByParam(Vacancy vacancy)
            throws ConnectionLostException, WrongVacancyParamException;
}
