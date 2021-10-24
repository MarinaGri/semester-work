package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.ConnectionLostException;
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
    private final String LINK = "https://hh.ru/vacancy/" ;

    public VacanciesService(VacanciesRepository vacanciesRepository, DictionariesRepository dictionariesRepository){
        this.vacanciesRepository = vacanciesRepository;
        this.dictionariesRepository = dictionariesRepository;
    }

    //FIXME добавить страницу с ошибками и переписать нормально без IAEx
    public Map<String, String> getExperience(){
        try{
            return dictionariesRepository.getExperience();
        } catch (ConnectionLostException ex){
            throw new IllegalArgumentException();
        }
    }

    public Map<String, String> getEmployment(){
        try{
            return dictionariesRepository.getEmployment();
        } catch (ConnectionLostException ex){
            throw new IllegalArgumentException();
        }
    }

    public Map<String, String> getSchedule(){
        try{
            return dictionariesRepository.getSchedule();
        } catch (ConnectionLostException ex){
            throw new IllegalArgumentException();
        }
    }

    public List<Vacancy> getVacancies(Vacancy vacancy){
        try {
            Optional<List<Vacancy>> optionalVacancies = vacanciesRepository.getVacanciesByParam(vacancy);
            return optionalVacancies.orElse(null);
        } catch (ConnectionLostException | WrongVacancyParamException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getLink(int number){
        return LINK + number;
    }
}
