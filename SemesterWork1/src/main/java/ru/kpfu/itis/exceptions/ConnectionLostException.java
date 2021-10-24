package ru.kpfu.itis.exceptions;

public class ConnectionLostException extends Exception{

    public ConnectionLostException(){
        super();
    }

    public ConnectionLostException(String message) {
        super(message);
    }

    public ConnectionLostException(String message, Throwable cause) {
        super(message, cause);
    }
}
