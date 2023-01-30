package model.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

public class Lesson {

    private final Weekday day;
    private final Time time;
    private final int courseId;
    private final Duration duration;
    private final Date fromDate;
    private final int numOfWeeks;

    public Lesson(Weekday day, Time time, int courseId, Duration duration, Date fromDate, int numOfWeeks) {
        this.day = day;
        this.time = time;
        this.courseId = courseId;
        this.duration = duration;
        this.fromDate = fromDate;
        this.numOfWeeks = numOfWeeks;
    }


    public String getDay() {
        return day.toString();
    }

    public Time getTime() {
        return time;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getDuration() {
        return (int)duration.toMinutes();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public int getNumOfWeeks() {
        return numOfWeeks;
    }


}