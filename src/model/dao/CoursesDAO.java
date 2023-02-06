package model.dao;

import exception.DAOException;
import model.domain.Course;
import model.domain.CoursesList;
import java.sql.*;

public class CoursesDAO implements GenericProcedureDAO<CoursesList> {

    @Override
    public CoursesList execute(Object... params) throws DAOException {
        CoursesList courses = new CoursesList();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ottieni_corsi()}");
            boolean status = cs.execute();

            if (status) {
                ResultSet rs = cs.getResultSet();
                if (rs.first()) {
                    do {
                        Course course = new Course(rs.getString(2), rs.getBigDecimal(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(7));

                        course.setId(rs.getInt(1));
                        course.setStartingDate(rs.getDate(6));
                        courses.addCourse(course);
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: " + e.getMessage());
        }

        return courses;
    }

}