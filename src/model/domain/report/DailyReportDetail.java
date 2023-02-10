package model.domain.report;

import model.domain.BaseEntityForList;

import java.sql.Date;

public class DailyReportDetail implements BaseEntityForList {

    private final Date date;
    private final Integer entrances;
    private final Integer subscriptions;

    public DailyReportDetail(Date date, Integer entrances, Integer subscriptions) {
        this.date = date;
        this.entrances = entrances;
        this.subscriptions = subscriptions;
    }

    @Override
    public String getValueByAttributeName(String attributeName) {
        String value = null;
        switch (attributeName){
            case "data" -> value = date.toString();
            case "numeroIngressi" -> value = entrances.toString();
            case "numeroIscritti" -> value = subscriptions.toString();
        }

        return value;
    }
}
