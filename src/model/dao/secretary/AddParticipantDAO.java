package model.dao.secretary;


import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Participant;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AddParticipantDAO implements GenericProcedureDAO<Participant> {

    private static AddParticipantDAO instance = null;
    private AddParticipantDAO(){}

    public static AddParticipantDAO getInstance(){
        if(instance == null){
            instance = new AddParticipantDAO();
        }
        return instance;
    }

    @Override
    public Participant execute(Object... params) throws DAOException {
        String code = (String) params[0];
        String name = (String) params[1];
        String address = (String) params[2];
        int houseNumber = (int) params[3];
        String postalCode = (String) params[4];
        String contactsJson = (String) params[5];
        int courseId = (int) params[6];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_partecipante(?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setString(2, name);
            cs.setString(3, address);
            cs.setInt(4, houseNumber);
            cs.setString(5, postalCode);
            cs.setString(6, contactsJson);
            cs.setInt(7, courseId);
            cs.executeQuery();

        } catch(SQLException e) {
            throw new DAOException("Errore: " + e.getMessage());
        }

        return new Participant(code, name, address, houseNumber, postalCode);
    }
}
