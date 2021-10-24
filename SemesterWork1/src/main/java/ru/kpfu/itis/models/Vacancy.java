package ru.kpfu.itis.models;

import java.util.StringJoiner;

public class Vacancy {
    private Integer id;
    private Integer number;
    private String name;
    private String text;
    private String searchField;
    private String area;
    private String experience;
    private String employment; //тип занятости
    private String schedule;
    private String salary;
    private String onlyWithSalary;
    private String requirement;
    private String responsibility;
    private String link;

    public Vacancy(String text, String experience, String employment, String schedule, String salary, String onlyWithSalary) {
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

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getSearchField() {
        return searchField;
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

    public String getOnlyWithSalary() {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Vacancy.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("number=" + number)
                .add("name='" + name + "'")
                .add("text='" + text + "'")
                .add("searchField='" + searchField + "'")
                .add("area='" + area + "'")
                .add("experience='" + experience + "'")
                .add("employment='" + employment + "'")
                .add("schedule='" + schedule + "'")
                .add("salary='" + salary + "'")
                .add("onlyWithSalary='" + onlyWithSalary + "'")
                .add("requirement='" + requirement + "'")
                .add("responsibility='" + responsibility + "'")
                .toString();
    }
}