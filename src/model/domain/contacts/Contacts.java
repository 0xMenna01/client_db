package model.domain.contacts;

import org.json.*;
import java.util.ArrayList;
import java.util.List;


public class Contacts {

    private final List<String> emails = new ArrayList<>();
    private final List<String> cellPhones = new ArrayList<>();
    private final List<String> telephones = new ArrayList<>();

    public void addEmail(String email){
        this.emails.add(email);
    }

    public void addCellPhone(String cellPhone){
        this.cellPhones.add(cellPhone);
    }

    public void addTelephone(String telephone){
        this.telephones.add(telephone);
    }

    @Override
    public String toString() {
        JSONObject contacts = new JSONObject();

        JSONArray emailsJson = new JSONArray();
        JSONArray cellPhonesJson = new JSONArray();
        JSONArray telephonesJson = new JSONArray();

        for(String email: emails){
            emailsJson.put(email);
        }
        contacts.put("email", emailsJson);

        for(String cellphone: cellPhones){
            cellPhonesJson.put(cellphone);
        }
        contacts.put("cell", cellPhonesJson);

        for (String telephone: telephones){
            telephonesJson.put(telephone);
        }
        contacts.put("tel", telephonesJson);

        return contacts.toString();

    }

}
