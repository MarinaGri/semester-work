package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.exceptions.LoadingDataException;
import ru.kpfu.itis.exceptions.RemovalFailedException;
import ru.kpfu.itis.exceptions.SavingFailedException;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Post;
import ru.kpfu.itis.repositories.CommentsRepository;
import ru.kpfu.itis.repositories.PostsRepository;

import java.util.List;
import java.util.Optional;

public class PublicationService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    public PublicationService(CommentsRepository commentsRepository, PostsRepository postsRepository){
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
    }

    public void postComment(Comment comment) throws SavingFailedException {
        try{
            commentsRepository.save(comment);
        } catch (DbException ex){
            throw new SavingFailedException("Failed to save comment's data", ex);
        }
    }

    public List<Comment> getCommentsByNumVacancy(Integer num) throws LoadingDataException {
        try{
            Optional<List<Comment>> optionalComments = commentsRepository.findByNumVacancy(num);
            return optionalComments.orElse(null);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load comments", ex);
        }
    }

    public List<Comment> getCommentsByPostId(Integer num) throws LoadingDataException {
        try{
            Optional<List<Comment>> optionalComments = commentsRepository.findByPostId(num);
            return optionalComments.orElse(null);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load comments", ex);
        }
    }

    public List<Post> findPosts(int id) throws LoadingDataException{
        try{
            Optional<List<Post>> optionalPosts = postsRepository.findByAccountId(id);
            return optionalPosts.orElse(null);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load posts", ex);
        }
    }

    public Integer getNumOfPosts(Integer id) throws LoadingDataException {
        try{
            return postsRepository.getNumOfPosts(id);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load posts", ex);
        }
    }

    public void savePost(Post post) throws SavingFailedException {
        try{
            postsRepository.save(post);
        } catch (DbException ex){
            throw new SavingFailedException("Failed to save post's data", ex);
        }
    }

    public void updatePost(Post post) throws LoadingDataException {
        try{
            postsRepository.updatePost(post);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to update post", ex);
        }
    }

    public void deletePost(Post post) throws RemovalFailedException {
        try{
            postsRepository.delete(post);
        } catch (DbException ex){
            throw new RemovalFailedException("Failed to delete post", ex);
        }
    }

    public void updateComment(Comment comment) throws LoadingDataException {
        try{
            commentsRepository.update(comment);
        } catch (DbException ex){
            throw new LoadingDataException("Failed to load comment", ex);
        }
    }

    public void deleteComment(Comment comment) throws RemovalFailedException {
        try{
            commentsRepository.delete(comment);
        } catch (DbException ex){
            throw new RemovalFailedException("Failed to delete comment", ex);
        }
    }
}
