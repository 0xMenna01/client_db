package view;

import model.domain.Course;
import model.domain.Lesson;
import model.domain.Weekday;
import utils.Input;
import utils.SecretaryOption;
import view.components.SecretaryComponents;

import java.io.IOException;
import java.math.BigDecimal;


public class SecretaryView {
    public static SecretaryOption showMenu() throws IOException {
        SecretaryComponents.showMenuBanner();
        SecretaryComponents.showSelectionList();

        int choice;
        while (true) {

            System.out.print("Please enter your choice: ");
            choice = Input.readInt();
            if (choice >= 1 && choice <= 11) {
                break;
            }
            System.out.println("Invalid option");

        }

        return SecretaryOption.fromInt(choice);
    }

    public static Course provideCourse() throws IOException {
        SecretaryComponents.showAddCourseBanner();

        System.out.print("NAME: ");
        String name = Input.readLine();

        System.out.print("PRICE: ");
        BigDecimal price = Input.readDecimal();

        System.out.print("MINIMUM PARTICIPANTS: ");
        int min = Input.readInt();

        System.out.print("MAXIMUM PARTICIPANTS: ");
        int max = Input.readInt();

        System.out.print("POOL NUMBER: ");
        int pool = Input.readInt();

        return new Course(name, price, min, max, pool);


    }




    public static Lesson provideLesson() throws IOException {

        SecretaryComponents.showLessonBanner();
        System.out.println("In che giorno si terrà la lezione ?");
        SecretaryComponents.showDaysLesson();
        System.out.print("Inserire il numero del giorno scelto: ");

        int choice = Input.readInt();

        Weekday day = Weekday.fromInt(choice);


        return new Lesson();



    }

    public static int showErrorMessage(String message){
        System.out.println(message);

        int choice;
        while(true){

            System.out.print("Type 1 if you want to insert the course again, else 0: ");
            choice = Input.readInt();
            if(choice == 0 || choice == 1){
                break;
            }
            System.out.println("Invalid option");

        }

        return choice;
    }

    public static void showMessage(String message){
        System.out.println(message);
    }

    public static void next() throws IOException {
        System.out.print("Press ENTER to continue: ");
        Input.readLine();
    }



}

