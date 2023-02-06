package model.dao;

import exception.DAOException;
import model.domain.Course;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AddCourseDAO implements GenericProcedureDAO<Course>{

    @Override
    public Course execute(Object... params) throws DAOException {
        String name = (String) params[0];
        BigDecimal price = (BigDecimal) params[1];
        int min = (int) params[2];
        int max = (int) params[3];
        int pool = (int) params[4];

        int courseId;
        Course course;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_corso(?,?,?,?,?,?)}");
            cs.setString(1, name);
            cs.setBigDecimal(2, price);
            cs.setInt(3, min);
            cs.setInt(4, max);
            cs.setInt(5, pool);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeQuery();
            courseId = cs.getInt(6);
        } catch(SQLException e) {
            throw new DAOException("Error: " + e.getMessage());
        }


        course = new Course(name, price, min, max, pool);
        course.setId(courseId);

        return course;
    }
}
