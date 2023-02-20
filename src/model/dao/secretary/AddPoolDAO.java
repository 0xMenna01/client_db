package model.dao.secretary;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AddPoolDAO implements GenericProcedureDAO<Pool> {


    private static AddPoolDAO instance = null;
    private AddPoolDAO(){}

    public static AddPoolDAO getInstance(){
        if(instance == null){
            instance = new AddPoolDAO();
        }
        return instance;
    }

    @Override
    public Pool execute(Object... params) throws DAOException {

        int poolId;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_vasca(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeQuery();
            poolId = cs.getInt(1);
        } catch(SQLException e) {
            throw new DAOException("Errore: " + e.getMessage());
        }

        return new Pool(poolId);
    }

}
