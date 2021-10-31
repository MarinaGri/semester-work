package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountsRepository;
import ru.kpfu.itis.validators.InputValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SecurityServiceImpl implements SecurityService{
    private final AccountsRepository accountsRepository;
    private final InputValidator inputValidator;


    public SecurityServiceImpl(AccountsRepository accountsRepository, InputValidator inputValidator) {
        this.accountsRepository = accountsRepository;
        this.inputValidator = inputValidator;
    }

    //FIXME добавить страницу с ошибками и переписать нормально без IAEx
    @Override
    public boolean isSignIn(Account account) {
        Optional<Account> accountOptional;
        try {
            accountOptional = accountsRepository.findByEmail(account.getEmail());
        } catch (DbException e) {
            throw new IllegalArgumentException(e);
        }

        if (accountOptional.isEmpty()) {
            return false;
        }

        Account accountRes = accountOptional.get();

        return accountRes.getPassword().equals(account.getPassword());
    }

    @Override
    public Account isExist(String email) {
        Optional<Account> accountOptional;
        try {
            accountOptional = accountsRepository.findByEmail(email);
        } catch (DbException e) {
            throw new IllegalArgumentException(e);
        }
        return accountOptional.orElse(null);
    }

    @Override
    public void signUp(Account account) {
        try {
            accountsRepository.save(account);
        } catch (DbException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isValidAccount(HttpServletRequest request, Account account) {
        String passRes = inputValidator.checkPassword(account.getPassword());
        String emailRes = inputValidator.checkEmail(account.getEmail());
        String firstNameRes = inputValidator.checkName(account.getFirstName());
        String lastNameRes = inputValidator.checkName(account.getLastName());

        request.setAttribute("passwordTip", passRes);
        request.setAttribute("emailTip", emailRes);
        request.setAttribute("firstNameTip", firstNameRes);
        request.setAttribute("lastNameTip", lastNameRes);
        return passRes == null && emailRes == null && firstNameRes == null && lastNameRes == null;
    }

    @Override
    public boolean isValidSearchData(HttpServletRequest request, String keywords, String num) {
        String keywordsRes = inputValidator.checkWords(keywords);
        String numRes = inputValidator.checkNum(num);

        request.setAttribute("keywordTip", keywordsRes);
        request.setAttribute("salaryTip", numRes);
        return keywordsRes == null && numRes == null;

    }

    @Override
    public void deleteAccount(Account account) {
        try {
            accountsRepository.deleteAccount(account);
        } catch (DbException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
