package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.utils.DbWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... objects) throws DbException {
        List<T> objectList = new ArrayList<>();
        Connection conn = DbWrapper.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            int position = 1;
            for (Object obj : objects) {
                preparedStatement.setObject(position++, obj);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet != null) {
                    while (resultSet.next()){
                        objectList.add(rowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DbException("Connection to db failed", ex);
        }
        return objectList;
    }

    public <T> int update(String sql, Object... objects) throws DbException {
        int id = -1;
         Connection conn =  DbWrapper.getConnection();
         try {
             PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int position = 1;
            for (Object obj : objects) {
                preparedStatement.setObject(position++, obj);
            }
            preparedStatement.execute();

            try {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                while (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException ignored){}

        } catch (SQLException ex) {
            throw new DbException("Connection to db failed", ex);
        }
        return id;
    }
}
