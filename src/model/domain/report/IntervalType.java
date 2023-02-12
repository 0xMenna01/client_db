package model.domain.report;

public enum IntervalType {

    DAILY(1), WEEKLY(2), MONTHLY(3);

    private final int id;

    private IntervalType(int id) {
        this.id = id;
    }

    public static IntervalType fromInt(int id) {
        for (IntervalType interval : values()) {
            if (interval.getId() == id) {
                return interval;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){

        String interval = null;
        switch (this){
            case DAILY -> interval = "giorni";
            case WEEKLY -> interval = "settimane";
            case MONTHLY -> interval = "mesi";

        }

        return interval;

    }
}
