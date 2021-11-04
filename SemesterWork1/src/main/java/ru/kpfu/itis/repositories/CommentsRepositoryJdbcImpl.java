package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Comment;

import java.util.List;
import java.util.Optional;

public class CommentsRepositoryJdbcImpl implements CommentsRepository{
    //language=SQL
    private final String SQL_INSERT = "insert into comment(text, account_id, num_vacancy, post_id) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_BY_NUM_VACANCY = "select * from comment where num_vacancy = ?";

    //language=SQL
    private final String SQL_SELECT_BY_POST_ID = "select * from comment where post_id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID = "delete from comment where id = ?";

    //language=SQL
    private final String SQL_UPDATE = "update comment set text = ?, account_id = ?, num_vacancy = ?, post_id = ? where id = ?";

    private RowMapper<Comment> rowMapper =  resultSet -> {
        int id = resultSet.getInt("id");
        String text = resultSet.getString("text");
        Integer accountId = resultSet.getInt("account_id");
        Integer numVacancy = resultSet.getInt("num_vacancy");
        Integer postId = resultSet.getInt("post_id");

        return new Comment(id, text, accountId, numVacancy, postId);
    };

    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public CommentsRepositoryJdbcImpl() {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate();
    }

    @Override
    public void save(Comment comment) throws DbException {
        int id = simpleJdbcTemplate.update(SQL_INSERT, comment.getText(),
                comment.getAccountId(), comment.getNumVacancy(), comment.getPostId());
        if(id != -1){
            comment.setId(id);
        }
    }

    @Override
    public Optional<List<Comment>> findByNumVacancy(Integer num) throws DbException {
        List<Comment> comments = simpleJdbcTemplate.query(SQL_SELECT_BY_NUM_VACANCY, rowMapper, num);
        return Optional.of(comments);
    }

    @Override
    public Optional<List<Comment>> findByPostId(Integer id) throws DbException {
        List<Comment> comments = simpleJdbcTemplate.query(SQL_SELECT_BY_POST_ID, rowMapper, id);
        return Optional.of(comments);
    }

    @Override
    public void update(Comment comment) throws DbException {
        System.out.println(comment);
        simpleJdbcTemplate.update(SQL_UPDATE, comment.getText(),
                comment.getAccountId(), comment.getNumVacancy(), comment.getPostId(), comment.getId());
    }

    @Override
    public void delete(Comment comment) throws DbException {
        simpleJdbcTemplate.update(SQL_DELETE_BY_ID, comment.getId());
    }
}
