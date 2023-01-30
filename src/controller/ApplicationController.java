package controller;

import model.domain.Credentials;

public class ApplicationController implements Controller {
    Credentials cred;

    @Override
    public void start() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if(!cred.isAuthorized()) {
            throw new RuntimeException("Invalid credentials");
        }

        new SecretaryController().start();
    }
}
