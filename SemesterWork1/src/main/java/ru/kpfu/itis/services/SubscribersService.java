package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.repositories.AccountsRepository;

import java.util.List;
import java.util.Optional;

public class SubscribersService {
    private final AccountsRepository accountsRepository;


    public SubscribersService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<Account> findSubs(Integer id) throws LoadingDataException {
        try {
            return accountsRepository.findSubsById(id).orElse(null);
        } catch (DbException ex) {
            throw new LoadingDataException("Failed to load subscribers", ex);
        }
    }

    public Integer getNumOfSubs(Integer id) throws LoadingDataException {
        try{
            return accountsRepository.getNumOfSubs(id);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load subscribers", ex);
        }
    }

    public Account findById(Integer id) throws LoadingDataException {
        try{
            return accountsRepository.findById(id).orElse(null);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load subscriber", ex);
        }
    }

    public boolean isSubscriber(Integer accountId, Integer subsId) throws LoadingDataException {
        try {
            Optional<List<Integer>> optionalIds = accountsRepository.getSubscribersId(accountId);
            if(optionalIds.isEmpty()) return false;
            List<Integer> ids = optionalIds.get();
            for(Integer id: ids){
                if(subsId.equals(id)) return true;
            }
            return false;
        } catch (DbException ex) {
            throw new LoadingDataException("Failed to load subscriber", ex);
        }
    }

    public void subscribe(Integer accountId, Integer subsId) throws SavingFailedException {
        try{
            accountsRepository.addSubscription(accountId, subsId);
        } catch (DbException ex){
            throw new SavingFailedException("Failed to subscribe", ex);
        }
    }

    public void unsubscribe(Integer accountId, Integer subsId) throws RemovalFailedException {
        try{
            accountsRepository.deleteSubscription(accountId, subsId);
        } catch (DbException ex){
            throw new RemovalFailedException("Failed to unsubscribe", ex);
        }
    }
}
