package model.dao.secretary;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.ListForTable;
import model.domain.Participant;
import utils.ListFactoryDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantsDAO implements GenericProcedureDAO<ListForTable<Participant>> {

    private static ParticipantsDAO instance = null;
    private ParticipantsDAO(){}

    public static ParticipantsDAO getInstance(){
        if(instance == null){
            instance = new ParticipantsDAO();
        }
        return instance;
    }

    @Override
    public ListForTable<Participant> execute(Object... params) throws DAOException {
        ListForTable<Participant> participantsList = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ottieni_partecipanti()}");
            boolean status = cs.execute();

            if (status) {
                ResultSet rs = cs.getResultSet();
                participantsList = new ListFactoryDAO<Participant>().getListByAttributes(rs);

                while(rs.next()){
                    Participant participant = new Participant(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getString(5));

                    participant.setRecentSubscription(rs.getDate(6));

                    participantsList.addToList(participant);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: " + e.getMessage());
        }

        return participantsList;
    }
}
