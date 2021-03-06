package ru.kpfu.itis.utils;

import ru.kpfu.itis.exceptions.ConnectionLostException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class APIWrapper {
    private static final String URL_VACANCIES = "https://api.hh.ru/vacancies?";
    private static final String URL_DICTIONARIES = "https://api.hh.ru/dictionaries";
    private static final String OAUTH_URL = "https://login.yandex.ru/info?oauth_token=";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static URLConnection vacanciesConn;
    private static URLConnection dictionariesConn;
    private static URLConnection oAuthConn;

    public static URLConnection getVacanciesConn(String params) throws ConnectionLostException {
            try {
                vacanciesConn = new URL(URL_VACANCIES + params).openConnection();
                vacanciesConn.setRequestProperty("User-Agent", USER_AGENT);
            } catch (IOException ex) {
                throw new ConnectionLostException("Can't access hh.ru", ex);
            }

        return vacanciesConn;
    }

    public static URLConnection getDictionariesConn() throws ConnectionLostException {
            try {
                dictionariesConn = new URL(URL_DICTIONARIES).openConnection();
                dictionariesConn.setRequestProperty("User-Agent", USER_AGENT);
            } catch (IOException ex) {
                throw new ConnectionLostException("Can't access hh.ru", ex);
            }

        return dictionariesConn;
    }

    public static URLConnection getOAuthConn(String token) throws ConnectionLostException{
        try {
            oAuthConn = new URL(OAUTH_URL + token).openConnection();
        } catch (IOException ex) {
            throw new ConnectionLostException("Can't access yandex id", ex);
        }
        return oAuthConn;
    }
}
