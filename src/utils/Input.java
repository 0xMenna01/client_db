package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {


    public static String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static int readInt(){
        int choice;
        Scanner input = new Scanner(System.in);

        while(true){

            if (input.hasNextInt()) {
                choice = input.nextInt();
                break;
            }
            System.out.print("Il valore deve essere un numero valido: ");
            input.next();
        }

        return choice;
    }

    public static BigDecimal readDecimal() throws InputMismatchException{
        BigDecimal choice;
        Scanner input = new Scanner(System.in);

        while(true){
            if (input.hasNextBigDecimal()) {
                choice = input.nextBigDecimal();
                break;
            }
            System.out.print("Il valore deve essere un numero valido: ");
            input.next();
        }

        return choice;
    }

    public static Time readTime() throws IOException {
        Time choice;
        String input;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setLenient(false);

        while(true){
            input = readLine();
            input = input.concat(":00");

            try {
                Date date = dateFormat.parse(input);
                choice = new Time(date.getTime());
                break;
            } catch (ParseException e) {
                System.out.print("Formato non valido (HH::mm): ");
            }
        }

        return choice;

    }

    public static Duration readDurationTime() throws IOException {

        return Duration.between(LocalTime.MIDNIGHT, readTime().toLocalTime());
    }

    public static Date readDate() throws IOException {
        Date choice;
        String input;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        while(true){
            input = readLine();

            try {
                choice = dateFormat.parse(input);
                break;
            } catch (ParseException e) {
                System.out.print("Formato non valido (yyyy-mm-gg): ");
            }
        }

        return choice;
    }
}
