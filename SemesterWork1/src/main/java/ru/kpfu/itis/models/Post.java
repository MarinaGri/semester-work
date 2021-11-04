package ru.kpfu.itis.models;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Post {
    private Integer id;
    private String title;
    private String text;
    private Integer accountId;
    private List<Comment> comments;

    public Post(Integer id, String title, String text, Integer accountId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.accountId = accountId;
    }

    public Post(String title, String text, Integer accountId) {
        this.title = title;
        this.text = text;
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("text='" + text + "'")
                .add("accountId=" + accountId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(text, post.text) &&
                Objects.equals(accountId, post.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, accountId);
    }
}
