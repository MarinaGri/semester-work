package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Comment;

import java.util.List;
import java.util.Optional;

public class CommentsRepositoryJdbcImpl implements CommentsRepository{
    //language=SQL
    private final String SQL_INSERT = "insert into comment(text, num_vacancy) " +
            "values (?, ?)";

    //language=SQL
    private final String SQL_SELECT_BY_NUM_VACANCY = "select * from comment where num_vacancy = ?";

    private RowMapper<Comment> rowMapper =  resultSet -> {
        int id = resultSet.getInt("id");
        String text = resultSet.getString("text");
        Integer numVacancy = resultSet.getInt("num_vacancy");

        return new Comment(id, text, numVacancy);
    };

    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public CommentsRepositoryJdbcImpl() {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate();
    }

    @Override
    public void save(Comment comment) throws DbException {
        int id = simpleJdbcTemplate.update(SQL_INSERT, comment.getText(), comment.getNumVacancy());
        if(id != -1){
            comment.setId(id);
        }
    }

    @Override
    public Optional<List<Comment>> findByNumVacancy(Integer num) throws DbException {
        List<Comment> comments = simpleJdbcTemplate.query(SQL_SELECT_BY_NUM_VACANCY, rowMapper, num);
        return Optional.of(comments);
    }
}
