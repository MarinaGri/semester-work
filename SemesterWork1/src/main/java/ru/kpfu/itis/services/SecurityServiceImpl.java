package ru.kpfu.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountsRepository;
import ru.kpfu.itis.validators.InputValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SecurityServiceImpl implements SecurityService{
    private final AccountsRepository accountsRepository;
    private final InputValidator inputValidator;
    private final PasswordEncoder passwordEncoder;


    public SecurityServiceImpl(AccountsRepository accountsRepository, InputValidator inputValidator,
                               PasswordEncoder passwordEncoder) {
        this.accountsRepository = accountsRepository;
        this.inputValidator = inputValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isSignIn(Account account) throws LoadingDataException {
        Optional<Account> accountOptional;
        try {
            accountOptional = accountsRepository.findByEmail(account.getEmail());
        } catch (DbException ex) {
            throw new LoadingDataException("Failed to load account's data", ex);
        }

        if (accountOptional.isEmpty()) {
            return false;
        }

        Account accountRes = accountOptional.get();
        return passwordEncoder.matches(account.getPassword(), accountRes.getPassword());
    }

    @Override
    public Account isExist(String email) throws LoadingDataException {
        Optional<Account> accountOptional;
        try {
            accountOptional = accountsRepository.findByEmail(email);
        } catch (DbException ex) {
            throw new LoadingDataException("Failed to load account's data", ex);
        }
        return accountOptional.orElse(null);
    }

    @Override
    public void signUp(Account account) throws LoadingDataException {
        try {
            String password = passwordEncoder.encode(account.getPassword());
            account.setPassword(password);
            accountsRepository.save(account);
        } catch (DbException ex) {
            throw new LoadingDataException("Failed to load account's data", ex);
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
    public void deleteAccount(Account account) throws RemovalFailedException {
        try {
            accountsRepository.deleteAccount(account);
        } catch (DbException ex) {
            throw new RemovalFailedException("Failed to delete account", ex);
        }
    }
}
