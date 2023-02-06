package model.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;


public class Lesson {

    private final Weekday day;
    private final Time hour;
    private final Course course;
    private final Duration duration;
    private Date fromDate;
    private final int numOfWeeks;

    public Lesson(Weekday day, Time hour, Course course,
                  Duration duration, Date fromDate, int numOfWeeks) {
        this.day = day;
        this.hour = hour;
        this.course = course;
        this.duration = duration;
        this.fromDate = fromDate;
        this.numOfWeeks = numOfWeeks;
    }

    public Lesson(Weekday day, Time hour, Course course, Duration duration, int numOfWeeks) {
        this.day = day;
        this.hour = hour;
        this.course = course;
        this.duration = duration;
        this.numOfWeeks = numOfWeeks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(course.toString()).append(" ogni ").append(day.toString()).append(" alle ")
                .append(hour.toString()).append(" per ").append(numOfWeeks).append(" settimane");
        return sb.toString();

    }

    public Course getCourse() {
        return course;
    }

    public int getDayNumber() {
        return day.getId();
    }

    public Time getHour() {
        return hour;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public int getNumOfWeeks() {
        return numOfWeeks;
    }

    public int getDuration() {
        return (int)duration.toMinutes();
    }

}