package ru.kpfu.itis.exceptions;

public class RemovalFailedException extends Exception{

    public RemovalFailedException(){
        super();
    }

    public RemovalFailedException(String message) {
        super(message);
    }

    public RemovalFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

