package model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

public class Course {
    private int id;
    private String name;
    private BigDecimal price;
    private int minParticipants;
    private int maxParticipants;
    private Date startingDate;

    private int pool;

    public Course(String name, BigDecimal price, int minParticipants, int maxParticipants, int pool) {
        this.name = name;
        this.price = price;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.pool = pool;
    }

    public Course(int id){
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name + "(Codice: " + this.id + ")";
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }


    public int getPool() {
        return pool;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }
}
