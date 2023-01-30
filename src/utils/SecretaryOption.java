package utils;

public enum SecretaryOption {
    ADD_COURSE(1), ADD_LESSONS(2), ADD_PARTICIPANT(3), ENROLL_PARTICIPANT(4),
    ADD_POOL(5), REGISTER_ENTRANCE(6), ENTRANCES_REPORT(7), SHOW_COURSES(8),
    SHOW_PARTICIPANT_COURSES(9), SHOW_PARTICIPANTS(10), QUIT(11);
    private final int id;

    private SecretaryOption(int id) {
        this.id = id;
    }

    public static SecretaryOption fromInt(int id) {
        for (SecretaryOption option : values()) {
            if (option.getId() == id) {
                return option;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
