package model.dao;

import exception.DAOException;
import model.domain.Pool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AddPoolDAO implements GenericProcedureDAO<Pool>{

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
