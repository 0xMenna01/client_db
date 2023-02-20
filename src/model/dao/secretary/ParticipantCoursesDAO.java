package model.dao.secretary;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Course;
import model.domain.ListForTable;
import utils.ListFactoryDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantCoursesDAO implements GenericProcedureDAO<ListForTable<Course>> {

    private static ParticipantCoursesDAO instance = null;
    private ParticipantCoursesDAO(){}

    public static ParticipantCoursesDAO getInstance(){
        if(instance == null){
            instance = new ParticipantCoursesDAO();
        }
        return instance;
    }

    @Override
    public ListForTable<Course> execute(Object... params) throws DAOException {
        String participantCode = (String) params[0];
        ListForTable<Course> coursesList = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ottieni_corsi_partecipante(?)}");
            cs.setString(1, participantCode);
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
