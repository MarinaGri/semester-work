package ru.kpfu.itis.models;


public class Comment {
    private Integer id;
    private String text;
    private Integer numVacancy;

    public Comment(Integer id, String text, Integer numVacancy) {
        this.id = id;
        this.text = text;
        this.numVacancy = numVacancy;
    }

    public Comment(String text, Integer numVacancy) {
        this.text = text;
        this.numVacancy = numVacancy;
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
}
