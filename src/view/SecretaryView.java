package view;

import model.domain.Course;
import model.domain.Lesson;
import model.domain.Weekday;
import utils.Input;
import utils.SecretaryOption;
import utils.TablePrinter;
import view.components.SecretaryComponents;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;


public class SecretaryView {
    public static SecretaryOption showMenu() throws IOException {
        SecretaryComponents.showMenuBanner();
        SecretaryComponents.showSelectionList();

        int choice;
        while (true) {

            SecretaryComponents.showMessage("Inserire la scelta: ");
            choice = Input.readInt();
            if (choice >= 1 && choice <= 11) {
                break;
            }
            SecretaryComponents.showMessage("Opzione non valida");

        }

        return SecretaryOption.fromInt(choice);
    }

    public static Course provideCourse() throws IOException {
        SecretaryComponents.showAddCourseBanner();

        SecretaryComponents.showMessage("NOME: ");
        String name = Input.readLine();

        SecretaryComponents.showMessage("PREZZO: ");
        BigDecimal price = Input.readDecimal();

        SecretaryComponents.showMessage("NUMERO MINIMO DI PARTECIPANTI: ");
        int min = Input.readInt();

        SecretaryComponents.showMessage("NUMERO MASSIMO DI PARTECIPANTI: ");
        int max = Input.readInt();

        SecretaryComponents.showMessage("NUMERO VASCA: ");
        int pool = Input.readInt();

        return new Course(name, price, min, max, pool);


    }


    public static Lesson provideLesson(List<HashMap<String, String>> maps, List<String> columnNames) throws IOException {

        SecretaryComponents.showLessonBanner();

        SecretaryComponents.showMessage("Scegliere il corso per cui si vuole aggiungere la lezione\n\n");
        printTable(maps, columnNames);
        SecretaryComponents.showMessage("Inserire il codice del corso scelto: ");
        int courseId = Input.readInt();

        SecretaryComponents.showMessage("Scegliere il giorno della lezione\n");
        SecretaryComponents.showDaysLesson();

        SecretaryComponents.showMessage("Inserire il numero associato al giorno scelto: ");
        int choice = Input.readInt();
        Weekday day = Weekday.fromInt(choice);

        SecretaryComponents.showMessage("ORARIO (HH:mm): ");
        Time hour = Input.readTime();

        SecretaryComponents.showMessage("DURATA (HH:mm): ");
        Duration duration = Input.readDurationTime();

        SecretaryComponents.showMessage("A partire da quando vuoi inserire la lezione ogni " + day.toString() + " ?\n");
        SecretaryComponents.showMessage("DATA (YYYY-MM-gg): ");
        Date startingDate = new Date(Input.readDate().getTime());

        SecretaryComponents.showMessage("Per quante settimane si vuole aggiungere la lezione ?\n");
        SecretaryComponents.showMessage("NUMERO SETTIMANE: ");
        int numOfWeeks = Input.readInt();

        return new Lesson(day, hour, new Course(courseId), duration, startingDate, numOfWeeks);

    }




    public static void next() throws IOException {
        SecretaryComponents.showMessage("Premere INVIO per continuare: ");
        Input.readLine();
    }


    public static void printTable(List<HashMap<String, String>> maps, List<String> columnNames){
        TablePrinter.printTable(maps, columnNames);
    }

}

