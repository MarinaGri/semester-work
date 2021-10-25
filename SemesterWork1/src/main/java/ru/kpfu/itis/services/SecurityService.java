package ru.kpfu.itis.services;

import ru.kpfu.itis.models.Account;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isSignIn(Account account);
    boolean isExist(String email);
    void signUp(Account account);
    boolean isValidAccount(HttpServletRequest request, Account account);
    boolean isValidSearchData(HttpServletRequest request, String keywords, String num);
}
