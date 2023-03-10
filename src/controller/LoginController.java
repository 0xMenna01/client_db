package controller;

import exception.DAOException;
import model.dao.login.LoginProcedureDAO;
import model.domain.Credentials;
import view.LoginView;
import java.io.IOException;

public class LoginController implements Controller {
    private Credentials cred = null;

    @Override
    public void start() {
        try {
            cred = LoginView.authenticate();
            cred = new LoginProcedureDAO().execute(cred.getUsername(), cred.getPassword());
        } catch(IOException | DAOException e) {
            throw new RuntimeException(e);
        }


    }

    public Credentials getCred() {
        return cred;
    }
}

