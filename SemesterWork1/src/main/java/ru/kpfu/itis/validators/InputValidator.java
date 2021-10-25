package ru.kpfu.itis.validators;

public interface InputValidator {

    String checkPassword(String password);
    String checkEmail(String email);
    String checkDate(String date);
    String checkName(String name);
    String checkWords(String words);
    String checkNum(String num);
}
