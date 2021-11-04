package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Post;
import ru.kpfu.itis.utils.DbWrapper;

import java.sql.*;
import java.util.*;

public class PostsRepositoryJdbcImpl implements PostsRepository{
    //language=SQL
    private final String SQL_INSERT = "insert into post(title, text, account_id) " +
            "values (?, ?, ?)";

    //language=SQL
    private final String SQL_UPDATE = "update post set title = ?, text = ? where id = ?;";

    //language=SQL
    private final String SQL_SELECT_BY_ACCOUNT_ID = "select * from post where account_id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID = "delete from post cascade where id = ?";

    //language=SQL
    private final String SQL_COUNT_BY_ACCOUNT_ID = "select count(id) as count from post where account_id = ?";

    private final RowMapper<Post> rowMapper = resultSet ->{
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String text = resultSet.getString("text");
        Integer accountId = resultSet.getInt("account_id");

        return new Post(id, title, text, accountId);
    };

    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public PostsRepositoryJdbcImpl(){
        this.simpleJdbcTemplate = new SimpleJdbcTemplate();
    }

    @Override
    public void save(Post post) throws DbException {
        int id = simpleJdbcTemplate.update(SQL_INSERT, post.getTitle(), post.getText(), post.getAccountId());
        if(id != -1){
            System.out.println(id);
            post.setId(id);
        }
    }

    @Override
    public Optional<List<Post>> findByAccountId(Integer id) throws DbException {
        List<Post> posts = simpleJdbcTemplate.query(SQL_SELECT_BY_ACCOUNT_ID, rowMapper, id);
        return Optional.of(posts);
    }

    @Override
    public void updatePost(Post post) throws DbException {
        simpleJdbcTemplate.update(SQL_UPDATE, post.getTitle(), post.getText(), post.getId());
    }

    @Override
    public void delete(Post post) throws DbException {
        simpleJdbcTemplate.update(SQL_DELETE_BY_ID, post.getId());
    }

    @Override
    public Integer getNumOfPosts(Integer accountId) throws DbException {
        List<Integer> integers = simpleJdbcTemplate.query(SQL_COUNT_BY_ACCOUNT_ID,
                row -> row.getInt("count"), accountId);
        if(integers.size() > 0) {
            return integers.get(0);
        }
        return null;
    }
}
