package ru.kpfu.itis.models;


import java.util.Objects;
import java.util.StringJoiner;

public class Comment {
    private Integer id;
    private String text;
    private Integer accountId;
    private Integer numVacancy;
    private Integer postId;

    public Comment(String text, Integer accountId, Integer numVacancy, Integer postId) {
        this.text = text;
        this.accountId = accountId;
        this.numVacancy = numVacancy;
        this.postId = postId;
    }


    public Comment(Integer id, String text, Integer accountId, Integer numVacancy, Integer postId) {
        this.id = id;
        this.text = text;
        this.accountId = accountId;
        this.numVacancy = numVacancy;
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumVacancy() {
        return numVacancy;
    }

    public void setNumVacancy(Integer numVacancy) {
        this.numVacancy = numVacancy;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(accountId, comment.accountId) &&
                Objects.equals(numVacancy, comment.numVacancy) &&
                Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, accountId, numVacancy, postId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("text='" + text + "'")
                .add("accountId=" + accountId)
                .add("numVacancy=" + numVacancy)
                .add("postId=" + postId)
                .toString();
    }
}
