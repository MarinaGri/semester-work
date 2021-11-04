package ru.kpfu.itis.repositories;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository {
    void save(Comment comment) throws DbException;
    Optional<List<Comment>> findByNumVacancy(Integer num) throws DbException;
    void update(Comment comment) throws DbException;
    void delete(Comment comment) throws DbException;
}
