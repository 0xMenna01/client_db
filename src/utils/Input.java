package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
            System.out.print("Il valore deve essere un numero: ");
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
}
