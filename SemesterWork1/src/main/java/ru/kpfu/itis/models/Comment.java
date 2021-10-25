package ru.kpfu.itis.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Comment {
    private Integer id;
    private String text;
    private Vacancy vacancy;

    public Comment(Integer id, String text, Vacancy vacancy) {
        this.id = id;
        this.text = text;
        this.vacancy = vacancy;
    }

    public Comment(String text, Vacancy vacancy) {
        this.text = text;
        this.vacancy = vacancy;
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

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
