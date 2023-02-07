package model.domain.contacts;

public enum ContactType {
    EMAIL(1), TELEPHONE(2), CELLPHONE(3);

    private final int id;

    private ContactType(int id) {
        this.id = id;
    }

    public static ContactType fromInt(int id) {
        for (ContactType contact : values()) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

}

