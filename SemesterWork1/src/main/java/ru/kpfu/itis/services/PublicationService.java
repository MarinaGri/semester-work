package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.repositories.CommentsRepository;

import java.util.List;
import java.util.Optional;

public class PublicationService {
    private final CommentsRepository commentsRepository;

    public PublicationService(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }

    public void postComment(Comment comment){
        try{
            commentsRepository.save(comment);
        } catch (DbException ex){
            throw new IllegalArgumentException();
        }
    }

    public List<Comment> getCommentsByNumVacancy(Integer num){
        try{
            Optional<List<Comment>> optionalComments = commentsRepository.findByNumVacancy(num);
            return optionalComments.orElse(null);
        } catch (DbException ex){
            throw new IllegalArgumentException();
        }
    }
}
