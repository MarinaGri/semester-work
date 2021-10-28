package ru.kpfu.itis.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Post {
    private Integer id;
    private String title;
    private String text;
    private Integer accountId;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("text='" + text + "'")
                .add("accountId=" + accountId)
                .toString();
    }
}
