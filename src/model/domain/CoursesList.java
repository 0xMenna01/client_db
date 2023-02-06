package model.domain;

import java.util.ArrayList;
import java.util.List;

public class CoursesList {

    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String[] getCoursesInfo(){
        String[] courseInfo = new String[6];
        courseInfo[0] = "Codice";
        courseInfo[1] = "Nome";
        courseInfo[2] = "Prezzo";
        courseInfo[3] = "Min";
        courseInfo[4] = "Max";
        courseInfo[5] = "Data inizio";

        return courseInfo;

    }
}

