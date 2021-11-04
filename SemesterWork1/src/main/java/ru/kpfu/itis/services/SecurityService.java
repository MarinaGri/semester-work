package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.models.Account;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isSignIn(Account account) throws LoadingDataException;
    Account isExist(String email) throws LoadingDataException;
    void signUp(Account account) throws LoadingDataException;
    boolean isValidAccount(HttpServletRequest request, Account account);
    boolean isValidSearchData(HttpServletRequest request, String keywords, String num);
    void deleteAccount(Account account) throws RemovalFailedException;
}
