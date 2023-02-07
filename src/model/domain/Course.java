package model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

public class Course implements BaseEntityForList{
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Date startingDate;

    private Integer pool;

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
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("Corso (Codice: ").append(id).append(")");
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartingDate() {
        return startingDate != null ? startingDate.toString(): "non definita";
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

    public void setName(String name) {
        this.name = name;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public String getValueByAttributeName(String attributeName) {
        String value = null;
        switch (attributeName){
            case "codice" -> value = id.toString();
            case "nome" -> value = name;
            case "prezzo" -> value = price.toString();
            case "minPartecipanti" -> value = minParticipants.toString();
            case "maxPartecipanti" -> value = maxParticipants.toString();
            case "dataInizio" -> value = getStartingDate();
            case "vasca" -> value = pool.toString();
        }

        return value;
    }
}
