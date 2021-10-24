package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository {
    void save(Account account) throws DbException;
    Optional<Account> findByEmail(String email) throws DbException;
    Optional<List<Account>> findAll() throws DbException;
}
