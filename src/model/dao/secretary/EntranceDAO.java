package model.dao.secretary;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Entrance;
import model.domain.Participant;
import java.sql.*;


public class EntranceDAO implements GenericProcedureDAO<Entrance> {


    private static EntranceDAO instance = null;
    private EntranceDAO(){}

    public static EntranceDAO getInstance(){
        if(instance == null){
            instance = new EntranceDAO();
        }
        return instance;
    }

    @Override
    public Entrance execute(Object... params) throws DAOException {
        String participantCode = (String) params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_ingresso_partecipante(?)}");
            cs.setString(1, participantCode);
            cs.executeQuery();
        } catch(SQLException e) {
            throw new DAOException("Errore: " + e.getMessage());
        }

        return new Entrance(new Participant(participantCode));
    }
}


