package model.dao.secretary;

import exception.DAOException;
import model.dao.GenericProcedureDAO;
import model.domain.Course;
import model.domain.Participant;
import java.sql.*;

public class EnrollParticipantDAO implements GenericProcedureDAO<Participant> {

    @Override
    public Participant execute(Object... params) throws DAOException {
        String code = (String) params[0];
        int courseId = (int) params[1];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call iscrivi_partecipante(?,?)}");
            cs.setString(1, code);
            cs.setInt(2, courseId);
            cs.executeQuery();
        } catch(SQLException e) {
            throw new DAOException("Errore: " + e.getMessage());
        }

        return new Participant(code, new Course(courseId));
    }
}
