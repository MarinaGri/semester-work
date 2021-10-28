package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.utils.DbWrapper;

import javax.crypto.spec.PSource;
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
    private final String SQL_INSERT = "insert into account(first_name, last_name, email, password) " +
            "values (?, ?, ?, ?)";

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
    public Optional<List<Account>> findAll() throws DbException {
        List<Account> accounts = simpleJdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
        return Optional.of(accounts);
    }
}
