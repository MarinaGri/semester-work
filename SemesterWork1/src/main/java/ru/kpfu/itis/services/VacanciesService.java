package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.WrongVacancyParamException;
import ru.kpfu.itis.models.Vacancy;
import ru.kpfu.itis.repositories.DictionariesRepository;
import ru.kpfu.itis.repositories.VacanciesRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VacanciesService {
    private final VacanciesRepository vacanciesRepository;
    private final DictionariesRepository dictionariesRepository;

    public VacanciesService(VacanciesRepository vacanciesRepository, DictionariesRepository dictionariesRepository){
        this.vacanciesRepository = vacanciesRepository;
        this.dictionariesRepository = dictionariesRepository;
    }

    public Map<String, String> getExperience() throws LoadingDataException {
        try{
            return dictionariesRepository.getExperience();
        } catch (ConnectionLostException ex){
            throw new LoadingDataException("Failed to load data from hh.ru", ex);
        }
    }

    public Map<String, String> getEmployment() throws LoadingDataException {
        try{
            return dictionariesRepository.getEmployment();
        } catch (ConnectionLostException ex){
            throw new LoadingDataException("Failed to load data from hh.ru", ex);
        }
    }

    public Map<String, String> getSchedule() throws LoadingDataException {
        try{
            return dictionariesRepository.getSchedule();
        } catch (ConnectionLostException ex){
            throw new LoadingDataException("Failed to load data from hh.ru", ex);
        }
    }

    public List<Vacancy> getVacancies(Vacancy vacancy) throws LoadingDataException {
        try {
            Optional<List<Vacancy>> optionalVacancies = vacanciesRepository.getVacanciesByParam(vacancy);
            return optionalVacancies.orElse(null);
        } catch (ConnectionLostException | WrongVacancyParamException ex) {
            throw new LoadingDataException("Failed to load data from hh.ru", ex);
        }
    }
}
