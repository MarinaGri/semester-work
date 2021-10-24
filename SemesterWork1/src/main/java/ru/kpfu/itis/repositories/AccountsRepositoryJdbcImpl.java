package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.utils.DbWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    @Override
    public void save(Account account) throws DbException {
        Connection conn = DbWrapper.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
            int i = 1;
            statement.setString(i++, account.getFirstName());
            statement.setString(i++, account.getLastName());
            statement.setString(i++, account.getEmail());
            statement.setString(i++, account.getPassword());
            statement.execute();
        } catch (SQLException ex) {
            throw new DbException();
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) throws DbException {
        Connection conn = DbWrapper.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return Optional.of(accountRowMapper.apply(res));
            }
        } catch (SQLException|IllegalArgumentException ex) {
            throw new DbException();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Account>> findAll() throws DbException {
        List<Account> accounts = new ArrayList<>();
        Connection conn = DbWrapper.getConnection();
        try {
            ResultSet res = conn.createStatement().executeQuery(SQL_SELECT_ALL);
            while(res.next()){
                accounts.add(accountRowMapper.apply(res));
            }
        } catch (SQLException ex) {
            throw new DbException();
        }
        return Optional.of(accounts);
    }

    private final Function<ResultSet, Account> accountRowMapper = row -> {
        try {
            int id = row.getInt("id");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            String email = row.getString("email");
            String password = row.getString("password");

            return new Account(id, firstName, lastName, email, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

}
