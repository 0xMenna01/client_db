package model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

public class Course {
    private int id;
    private final String name;
    private final BigDecimal price;
    private final int minParticipants;
    private final int maxParticipants;
    private Date startingDate;

    private final int pool;

    public Course(int id, String name, BigDecimal price, int minParticipants, int maxParticipants, int pool) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.pool = pool;
    }

    public Course(String name, BigDecimal price, int minParticipants, int maxParticipants, int pool) {
        this.name = name;
        this.price = price;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.pool = pool;
    }

    @Override
    public String toString() {
        return this.name + "(Codice: " + this.id + ")";
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

}
