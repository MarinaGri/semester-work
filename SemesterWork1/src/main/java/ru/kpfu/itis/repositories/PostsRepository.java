package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    void save(Post post) throws DbException;
    Optional<List<Post>> findByAccountId(Integer id) throws DbException;
    Integer getNumOfPosts(Integer accountId) throws DbException;
    void updatePost(Post post) throws DbException;
    void delete(Post post) throws DbException;
}
