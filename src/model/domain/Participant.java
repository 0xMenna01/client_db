package model.domain;

import model.domain.contacts.Contacts;

import java.util.Date;

public class Participant implements BaseEntityForList{

    private final String code;
    private String name;
    private String address;
    private Integer houseNumber;
    private String postalCode;
    private Course courseSubscription;
    private Contacts contacts;
    private Date recentSubscription;


    public Participant(String code, String name, String address, int houseNumber,
                       String postalCode, Course courseSubscription, Contacts contacts){
        this.code = code;
        this.name = name;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.courseSubscription = courseSubscription;
        this.contacts = contacts;
    }

    public Participant(String code, String name, String address, int houseNumber,
                       String postalCode){
        this.code = code;
        this.name = name;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public Participant(String code, Course courseSubscription){
        this.code = code;
        this.courseSubscription = courseSubscription;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Course getCourseSubscription() {
        return courseSubscription;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setRecentSubscription(Date recentSubscription) {
        this.recentSubscription = recentSubscription;
    }

    @Override
    public String getValueByAttributeName(String attributeName) {
        String value = null;
        switch (attributeName){
            case "codiceFiscale" -> value = code;
            case "nome" -> value = name;
            case "via" -> value = address;
            case "numeroCivico" -> value = houseNumber.toString();
            case "cap" -> value = postalCode;
            case "ultimaIscrizione" -> value = recentSubscription.toString();
        }

        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Partecipante (codiceFiscale: ").append(code).append(")").toString();
    }
}
