package view;

import model.domain.Credentials;
import utils.Input;
import view.components.LoginComponents;
import java.io.IOException;

public class LoginView {
    public static Credentials authenticate() throws IOException {
        LoginComponents.showLoginBanner();
        System.out.print("USERNAME: ");
        String username = Input.readLine();
        System.out.print("PASSWORD: ");
        String password = Input.readLine();

        return new Credentials(username, password, false);
    }

}

