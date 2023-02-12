package model.dao.secretary;

import exception.DAOException;
import model.dao.GenericProcedureDAO;
import model.domain.Course;
import model.domain.Lesson;
import model.domain.Weekday;
import java.sql.*;
import java.time.Duration;

public class AddLessonsDAO implements GenericProcedureDAO<Lesson> {

    @Override
    public Lesson execute(Object... params) throws DAOException {
        int numDay = (int) params[0];
        Time hour = (Time) params[1];
        int courseId = (int) params[2];
        int duration = (int) params[3];
        Date startingDate = (Date) params[4];
        int numOfWeeks = (int) params[5];

        Course course;
        String courseName;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_lezioni(?,?,?,?,?,?,?)}");
            cs.setInt(1, numDay);
            cs.setTime(2, hour);
            cs.setInt(3, courseId);
            cs.setInt(4, duration);
            cs.setDate(5, startingDate);
            cs.setInt(6, numOfWeeks);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.executeQuery();
            courseName = cs.getString(7);
        } catch(SQLException e) {
            throw new DAOException("Errore: " + e.getMessage());
        }

        course = new Course(courseId);
        course.setName(courseName);


        return new Lesson(Weekday.fromInt(numDay), hour, course, Duration.ofMinutes(duration), numOfWeeks);
    }
}