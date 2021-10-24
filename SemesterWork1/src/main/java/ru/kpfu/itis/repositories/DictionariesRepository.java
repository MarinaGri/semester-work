package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.ConnectionLostException;

import java.util.Map;

public interface DictionariesRepository {
    Map<String, String> getExperience() throws ConnectionLostException;
    Map<String, String> getEmployment() throws ConnectionLostException;
    Map<String, String> getSchedule() throws ConnectionLostException;
}
