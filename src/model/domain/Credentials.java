package model.domain;

public class Credentials {

        private final String username;
        private final String password;
        private final Boolean hasAccess;

        public Credentials(String username, String password, Boolean hasAccess) {
            this.username = username;
            this.password = password;
            this.hasAccess = hasAccess;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public Boolean isAuthorized() {
            return hasAccess;
        }
}
