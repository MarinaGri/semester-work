package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository {
    void save(Account account) throws DbException;
    Optional<Account> findByEmail(String email) throws DbException;
    Optional<Account> findById(Integer id) throws DbException;
    Optional<List<Account>> findAll() throws DbException;
    Optional<List<Account>> findSubsById(Integer id) throws DbException;
    Integer getNumOfSubs(Integer id) throws DbException;
    Optional<List<Integer>> getSubscribersId(Integer id) throws DbException;
    void deleteAccount(Account account)throws DbException;
    void addSubscription(Integer accId, Integer subsId) throws DbException;
    void deleteSubscription(Integer accId, Integer subsId) throws DbException;
    void saveWithToken(Account account) throws DbException;
    Optional<Account> findByToken(String token) throws DbException;
}
