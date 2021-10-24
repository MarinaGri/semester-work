package ru.kpfu.itis.services;

import ru.kpfu.itis.models.Account;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isSignIn(Account account);
    boolean isExist(String email);
    void signUp(Account account);
    boolean isValid(HttpServletRequest request, Account account);
}
