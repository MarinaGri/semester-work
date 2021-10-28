package ru.kpfu.itis.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
