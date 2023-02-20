package model.dao.login;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.GenericProcedureDAO;
import model.domain.Credentials;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO implements GenericProcedureDAO<Credentials> {


    @Override
    public Credentials execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        boolean isAuthorized;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.executeQuery();
            isAuthorized = cs.getBoolean(3);
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }


        return new Credentials(username, password, isAuthorized);
    }
}
