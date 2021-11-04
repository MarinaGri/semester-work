package ru.kpfu.itis.models;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Vacancy {
    private Integer number;
    private String name;
    private String text;
    private String area;
    private String experience;
    private String employment;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public boolean isOnlyWithSalary() {
        return onlyWithSalary;
    }

    public void setOnlyWithSalary(boolean onlyWithSalary) {
        this.onlyWithSalary = onlyWithSalary;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name) &&
                Objects.equals(text, vacancy.text) &&
                Objects.equals(area, vacancy.area) &&
                Objects.equals(experience, vacancy.experience) &&
                Objects.equals(employment, vacancy.employment) &&
                Objects.equals(schedule, vacancy.schedule) &&
                Objects.equals(salary, vacancy.salary) &&
                Objects.equals(requirement, vacancy.requirement) &&
                Objects.equals(responsibility, vacancy.responsibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, area, experience, employment, schedule, salary, requirement, responsibility);
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
