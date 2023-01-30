package model.domain;

public enum Weekday {

    MONDAY(1), TUESDAY(2), WEDNESDAY(3),

    THURSDAY(4), FRIDAY(5), SATURDAY(6),

    SUNDAY(7);

    private final int id;

    private Weekday(int id) {
        this.id = id;
    }

    public static Weekday fromInt(int id) {
        for (Weekday day : values()) {
            if (day.getId() == id) {
                return day;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){

        String day = null;
        switch (this){
            case MONDAY -> day = "Lunedì";
            case TUESDAY -> day = "Martedì";
            case WEDNESDAY -> day = "Mercoledì";
            case THURSDAY -> day = "Giovedì";
            case FRIDAY -> day = "Venerdì";
            case SATURDAY -> day = "Sabato";
            case SUNDAY -> day = "Domenica";
        }

        return day;

    }
}
