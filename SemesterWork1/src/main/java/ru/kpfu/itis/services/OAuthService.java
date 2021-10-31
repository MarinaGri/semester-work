package ru.kpfu.itis.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountsRepository;
import ru.kpfu.itis.utils.APIWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class OAuthService {
    private final AccountsRepository accountsRepository;


    public OAuthService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public void signInWithToken(Account account) throws SavingFailedException {
        if(account != null) {
            try {
                accountsRepository.saveWithToken(account);
            } catch (DbException ex) {
                throw new SavingFailedException("Failed to save user's data", ex);
            }
        }
    }

    public Account isExist(String token) throws LoadingDataException {
        Optional<Account> accountOptional;
        try {
            accountOptional = accountsRepository.findByToken(token);
        } catch (DbException ex) {
            throw new LoadingDataException("Search for user failed", ex);
        }
        return accountOptional.orElse(null);
    }

    public Account getAccountData(String token) throws ConnectionLostException {
        URLConnection conn = null;
        conn = APIWrapper.getOAuthConn(token);
        Account account;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            Gson gson = new Gson();
            JsonObject dictionariesData = gson.fromJson(reader, JsonObject.class);
            System.out.println(dictionariesData);
            String firstName = dictionariesData.get("first_name").getAsString();
            String lastName = dictionariesData.get("last_name").getAsString();
            String email = dictionariesData.get("login").getAsString() + "@yandex.ru";
            account = new Account(firstName, lastName, email);
            account.setToken(token);
        } catch (IOException ex){
            throw new ConnectionLostException("Can't access yandex id", ex);
        }
        return account;
    }
}
