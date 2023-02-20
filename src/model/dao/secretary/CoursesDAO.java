package model.dao.secretary;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Course;
import model.domain.ListForTable;
import utils.ListFactoryDAO;

import java.sql.*;

public class CoursesDAO implements GenericProcedureDAO<ListForTable<Course>> {

    private static CoursesDAO instance = null;
    private CoursesDAO(){}

    public static CoursesDAO getInstance(){
        if(instance == null){
            instance = new CoursesDAO();
        }
        return instance;
    }

    @Override
    public ListForTable<Course> execute(Object... params) throws DAOException {
        ListForTable<Course> coursesList = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ottieni_corsi()}");
            boolean status = cs.execute();

            if (status) {
                ResultSet rs = cs.getResultSet();
                coursesList = new ListFactoryDAO<Course>().getListByAttributes(rs);

                while(rs.next()){
                    Course course = new Course(rs.getString(2), rs.getBigDecimal(3),
                            rs.getInt(4), rs.getInt(5), rs.getInt(7));

                    course.setId(rs.getInt(1));
                    course.setStartingDate(rs.getDate(6));
                    coursesList.addToList(course);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: " + e.getMessage());
        }

        return coursesList;
    }

}