package ru.kpfu.itis.exceptions;

public class WrongVacancyParamException extends Exception{

    public WrongVacancyParamException(){
        super();
    }

    public WrongVacancyParamException(String message) {
        super(message);
    }

    public WrongVacancyParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
