package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Vacancy;
import ru.kpfu.itis.utils.DbWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CommentsRepositoryJdbcImpl implements CommentsRepository{
    //language=SQL
    private final String SQL_INSERT = "insert into comment(text, num_vacancy) " +
            "values (?, ?)";

    //language=SQL
    private final String SQL_SELECT_BY_NUM_VACANCY = "select * from comment where num_vacancy = ?";

    @Override
    public void save(Comment comment) throws DbException {
        Connection conn = DbWrapper.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            statement.setString(i++, comment.getText());
            statement.setInt(i++, comment.getVacancy().getNumber());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys ();
            Integer id = null;
            while(rs.next()){
                id = rs.getInt(1);
            }
            comment.setId(id);
        } catch (SQLException ex) {
            throw new DbException("Connection to db failed", ex);
        }
    }

    @Override
    public Optional<List<Comment>> findByNumVacancy(Integer num) throws DbException {
        List<Comment> comments = new ArrayList<>();
        Connection conn = DbWrapper.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_NUM_VACANCY);
            statement.setInt(1, num);

            ResultSet res = statement.executeQuery();

            while(res.next()){
                comments.add(commentRowMapper.apply(res));
            }
        } catch (SQLException ex) {
            throw new DbException("Connection to db failed", ex);
        }
        return Optional.of(comments);
    }

    private final Function<ResultSet, Comment> commentRowMapper = row -> {
        try {
            int id = row.getInt("id");
            String text = row.getString("text");
            Integer numVacancy = row.getInt("num_vacancy");

            return new Comment(id, text, new Vacancy(numVacancy));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };
}
