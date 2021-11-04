package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.utils.DbWrapper;

import javax.crypto.spec.PSource;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AccountsRepositoryJdbcImpl implements AccountsRepository {
    //language=SQL
    private final String SQL_SELECT_ALL = "select * from account";

    //language=SQL
    private final String SQL_SELECT_BY_EMAIL = "select * from account where email = ?";

    //language=SQL
    private final String SQL_SELECT_BY_TOKEN = "select * from account where token = ?";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "select * from account where id = ?";

    //language=SQL
    private final String SQL_INSERT = "insert into account(first_name, last_name, email, password) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_INSERT_WITH_TOKEN = "insert into account(first_name, last_name, email, token) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_SUBSCRIBERS_ID = "select account_id " +
            " from account left join subscription s " +
            "on account.id = s.subscriber_id where id = ? ";

    //language=SQL
    private final String SQL_SELECT_SUBSCRIBERS_BY_ID = "with cte_subscrip as(" + SQL_SELECT_SUBSCRIBERS_ID +") "+
                                                        "select * from cte_subscrip left join account " +
                                                        "on cte_subscrip.account_id = account.id";

    //language=SQL
    private final String SQL_COUNT_SUBS = "with cte_subscrip as(" + SQL_SELECT_SUBSCRIBERS_ID + ") "+
                                            "select count(*) from cte_subscrip as count " +
                                            "where account_id is not null ";

    //language=SQL
    private final String SQL_DELETE_ACC_BY_ID = "delete from account cascade where id = ?";

    //language=SQL
    private final String SQL_INSERT_INTO_SUBS = "insert into subscription(subscriber_id, account_id) values (?, ?)";

    //language=SQL
    private final String SQL_DELETE_SUBS_BY_ID = "delete from subscription " +
            "where subscriber_id = ? and account_id = ?";

    private final SimpleJdbcTemplate simpleJdbcTemplate;

    private final RowMapper<Account> rowMapper = resultSet -> {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Account(id, firstName, lastName, email, password);
    };

    public AccountsRepositoryJdbcImpl() {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate();
    }

    @Override
    public void save(Account account) throws DbException {
        int id = simpleJdbcTemplate.update(SQL_INSERT, account.getFirstName(),
                account.getLastName(), account.getEmail(), account.getPassword());
        if(id != -1){
            account.setId(id);
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_BY_EMAIL, rowMapper, email);
        if(accounts.size() != 0){
            return Optional.of(accounts.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> findById(Integer id) throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_BY_ID, rowMapper, id);
        if(accounts.size() != 0){
            System.out.println(accounts.get(0));
            return Optional.of(accounts.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Account>> findAll() throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
        return Optional.of(accounts);
    }

    @Override
    public Optional<List<Account>> findSubsById(Integer id) throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_SUBSCRIBERS_BY_ID, rowMapper, id);
        return Optional.of(accounts);
    }

    @Override
    public Integer getNumOfSubs(Integer id) throws DbException {
        List<Integer> integers = simpleJdbcTemplate.query(SQL_COUNT_SUBS,
                row -> row.getInt("count"), id);
        if(integers.size() > 0) {
            return integers.get(0);
        }
        return null;
    }

    @Override
    public Optional<List<Integer>> getSubscribersId(Integer id) throws DbException {
        List<Integer> integers = simpleJdbcTemplate.query(SQL_SELECT_SUBSCRIBERS_ID,
                row -> row.getInt("account_id"), id);
        return Optional.of(integers);
    }

    @Override
    public void deleteAccount(Account account) throws DbException {
        simpleJdbcTemplate.update(SQL_DELETE_ACC_BY_ID, account.getId());
    }

    @Override
    public void addSubscription(Integer accId, Integer subsId) throws DbException {
        simpleJdbcTemplate.update(SQL_INSERT_INTO_SUBS, accId, subsId);
    }

    @Override
    public void deleteSubscription(Integer accId, Integer subsId) throws DbException {
        simpleJdbcTemplate.update(SQL_DELETE_SUBS_BY_ID, accId, subsId);
    }

    @Override
    public void saveWithToken(Account account) throws DbException {
        int id = simpleJdbcTemplate.update(SQL_INSERT_WITH_TOKEN, account.getFirstName(),
                account.getLastName(), account.getEmail(), account.getToken());
        if(id != -1){
            account.setId(id);
        }
    }

    @Override
    public Optional<Account> findByToken(String token) throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_BY_TOKEN, rowMapper, token);
        if(accounts.size() != 0){
            return Optional.of(accounts.get(0));
        }
        return Optional.empty();
    }
}
