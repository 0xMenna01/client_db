package model.domain.report;

import model.domain.ListForTable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class ReportEntrances {

    private final Date fromDate;
    private final IntervalType interval;
    private int numOfDays;

    private Integer entrances;
    private Integer subscriptions;
    private ListForTable<DailyReportDetail> details;

    public ReportEntrances(Date fromDate, IntervalType interval) {
        this.fromDate = fromDate;
        this.interval = interval;
    }

    public ReportEntrances(Date fromDate, IntervalType interval, Integer entrances, Integer subscriptions, ListForTable<DailyReportDetail> details) {
        this.fromDate = fromDate;
        this.interval = interval;
        this.entrances = entrances;
        this.subscriptions = subscriptions;
        this.details = details;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public ListForTable<DailyReportDetail> getDetails() {
        return details;
    }

    public void setNumOfDays(int interval) {
        switch (this.interval){
            case DAILY -> numOfDays = interval;
            case WEEKLY -> numOfDays = 7*interval;
            case MONTHLY -> numOfDays = 30*interval;
        }
    }

    public String getFinalDate(){
        LocalDate initialDate = fromDate.toLocalDate();

        return initialDate.plus(Period.ofDays(numOfDays)).toString();
    }

    @Override
    public String toString() {
        return "DATA REPORT: ".concat(fromDate.toString())
                .concat(" - ").concat(getFinalDate()).concat("\n")
                .concat("INGRESSI EFFETTIVI: ").concat(entrances.toString()).concat("\n")
                .concat("INGRESSI PREVISTI: ").concat(subscriptions.toString());
    }
}
