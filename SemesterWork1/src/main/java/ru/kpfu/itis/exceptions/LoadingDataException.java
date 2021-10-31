package ru.kpfu.itis.exceptions;

public class LoadingDataException extends Exception{

    public LoadingDataException(){
        super();
    }

    public LoadingDataException(String message) {
        super(message);
    }

    public LoadingDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
