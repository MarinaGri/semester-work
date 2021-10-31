package ru.kpfu.itis.exceptions;

public class SavingFailedException extends Exception{

    public SavingFailedException(){
        super();
    }

    public SavingFailedException(String message) {
        super(message);
    }

    public SavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
