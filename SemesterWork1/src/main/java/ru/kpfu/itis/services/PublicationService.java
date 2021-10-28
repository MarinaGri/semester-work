package ru.kpfu.itis.services;

import ru.kpfu.itis.exceptions.DbException;
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

    public List<Post> findPosts(int id){
        try{
            Optional<List<Post>> optionalPosts = postsRepository.findByAccountId(id);
            return optionalPosts.orElse(null);
        } catch (DbException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    public Integer getNumOfPosts(Integer id){
        try{
            return postsRepository.getNumOfPosts(id);
        } catch (DbException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    public void savePost(Post post){
        try{
            postsRepository.save(post);
        } catch (DbException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    public void updatePost(Post post){
        try{
            postsRepository.updatePost(post);
        } catch (DbException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    public void deletePost(Post post){
        try{
            postsRepository.delete(post);
        } catch (DbException ex){
            throw new IllegalArgumentException(ex);
        }
    }
}
