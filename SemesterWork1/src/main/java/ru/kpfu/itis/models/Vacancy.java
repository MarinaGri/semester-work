package ru.kpfu.itis.models;

import java.util.List;
import java.util.StringJoiner;

public class Vacancy {
    private Integer number;
    private String name;
    private String text;
    private String area;
    private String experience;
    private String employment; //тип занятости
    private String schedule;
    private String salary;
    private boolean onlyWithSalary;
    private String requirement;
    private String responsibility;
    private String link;
    private List<Comment> comments;

    public Vacancy(String text, String salary, String experience, String employment, String schedule, boolean onlyWithSalary) {
        this.text = text;
        this.experience = experience;
        this.employment = employment;
        this.schedule = schedule;
        this.salary = salary;
        this.onlyWithSalary = onlyWithSalary;
    }

    public Vacancy(Integer number, String name, String area, String schedule,
                   String salary, String requirement, String responsibility, String link) {
        this.number = number;
        this.name = name;
        this.area = area;
        this.schedule = schedule;
        this.salary = salary;
        this.requirement = requirement;
        this.responsibility = responsibility;
        this.link = link;
    }

    public Vacancy(){}

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getArea() {
        return area;
    }

    public String getExperience() {
        return experience;
    }

    public String getEmployment() {
        return employment;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getSalary() {
        return salary;
    }

    public boolean getOnlyWithSalary() {
        return onlyWithSalary;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vacancy.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("name='" + name + "'")
                .add("text='" + text + "'")
                .add("area='" + area + "'")
                .add("experience='" + experience + "'")
                .add("employment='" + employment + "'")
                .add("schedule='" + schedule + "'")
                .add("salary='" + salary + "'")
                .add("onlyWithSalary='" + onlyWithSalary + "'")
                .add("requirement='" + requirement + "'")
                .add("responsibility='" + responsibility + "'")
                .add("comments" + comments)
                .toString();
    }
}
