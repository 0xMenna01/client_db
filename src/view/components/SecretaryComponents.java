package view.components;

import utils.Input;

import java.io.IOException;

public class SecretaryComponents {

    public static void showMenuBanner(){
        System.out.println("*********************************************************************");
        System.out.println();
        System.out.println("         __  __       _         __  __                  ");
        System.out.println("        |  \\/  | __ _(_)_ __   |  \\/  | ___ _ __  _   _ ");
        System.out.println("        | |\\/| |/ _` | | '_ \\  | |\\/| |/ _ \\ '_ \\| | | |");
        System.out.println("        | |  | | (_| | | | | | | |  | |  __/ | | | |_| |");
        System.out.println("        |_|  |_|\\__,_|_|_| |_| |_|  |_|\\___|_| |_|\\__,_|");
        System.out.println();
        System.out.println("                           .-\"\"\"-.");
        System.out.println("                         /       \\");
        System.out.println("                        ;_.-\"\"\"-._;");
        System.out.println("     .,_       __,.---.-(=(o)-(o)=)-.---.,__       _,.");
        System.out.println("     '._'--\"```          \\   ^   /          ```\"--'_.' ");
        System.out.println("        ``\"''~---~~%^%^.%.`._0_.'%,^%^%^~~---~''\"`` ");
        System.out.println("        ~^~- `^-% ^~.%~%.^~-%-~.%-^.% ~`% ~-`%^`-~^~ ");
        System.out.println("           ~^- ~^- `~.^- %`~.%~-'%~^- %~^- ~^ ");
        System.out.println();
    }

    public static void showSelectionList(){
        System.out.println("*********************************************************************");
        System.out.println("* Benvenuto nel sistema di gestione della psicina!                  *");
        System.out.println("*                                                                   *");
        System.out.println("* Scegliere una delle seguenti opzioni:                             *");
        System.out.println("*                                                                   *");
        System.out.println("* 1) Aggiungi un Corso                                              *");
        System.out.println("* 2) Aggiungi Lezioni settimanali                                   *");
        System.out.println("* 3) Registra un nuovo Partecipante                                 *");
        System.out.println("* 4) Iscrivi un partecipante ad un nuovo corso                      *");
        System.out.println("* 5) Aggiungi una vasca                                             *");
        System.out.println("* 6) Registra un ingresso                                           *");
        System.out.println("* 7) Fornisci un report degli ingressi                              *");
        System.out.println("* 8) Mostra tutti i corsi                                           *");
        System.out.println("* 9) Mostra i corsi di un partecipan                                *");
        System.out.println("* 10) Mostra le informazioni di un partecipante                     *");
        System.out.println("* 11) Esci                                                          *");
        System.out.println("*********************************************************************");
    }

    public static void showExitBanner() throws IOException{
        System.out.println();
        System.out.println("  ____                 _ _                ");
        System.out.println(" / ___| ___   ___   __| | |__  _   _  ___ ");
        System.out.println("| |  _ / _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\");
        System.out.println("| |_| | (_) | (_) | (_| | |_) | |_| |  __/");
        System.out.println(" \\____|\\___/ \\___/ \\__,_|_.__/ \\__, |\\___|");
        System.out.println("                               |___/      ");
    }


    public static void showAddCourseBanner(){
        System.out.println("*******************************************************************************************");
        System.out.println("        _               _                   _    ____                     ");
        System.out.println("       / \\   __ _  __ _(_)_   _ _ __   __ _(_)  / ___|___  _ __ ___  ___  ");
        System.out.println("      / _ \\ / _` |/ _` | | | | | '_ \\ / _` | | | |   / _ \\| '__/ __|/ _ \\ ");
        System.out.println("     / ___ \\ (_| | (_| | | |_| | | | | (_| | | | |__| (_) | |  \\__ \\ (_) |");
        System.out.println("    /_/   \\_\\__, |\\__, |_|\\__,_|_| |_|\\__, |_|  \\____\\___/|_|  |___/\\___/ ");
        System.out.println("             |___/ |___/               |___/                             ");
        System.out.println("********************************************************************************************");
    }


    public static void showLessonBanner(){
        System.out.println("**********************************************************************************************");
        System.out.println("         _               _                   _   _             _                  ");
        System.out.println("       / \\   __ _  __ _(_)_   _ _ __   __ _(_) | |    ___ ___(_) ___  _ __   ___ ");
        System.out.println("      / _ \\ / _` |/ _` | | | | | '_ \\ / _` | | | |   / _ \\_  / |/ _ \\| '_ \\ / _ \\");
        System.out.println("     / ___ \\ (_| | (_| | | |_| | | | | (_| | | | |__|  __// /| | (_) | | | |  __/");
        System.out.println("    /_/   \\_\\__, |\\__, |_|\\__,_|_| |_|\\__, |_| |_____\\___/___|_|\\___/|_| |_|\\___|");
        System.out.println("            |___/ |___/               |___/                                      ");
        System.out.println("                                                                                              ");
        System.out.println("**********************************************************************************************");
    }

    public static void showParticipantBanner(){
        System.out.println("*****************************************************************************************************************");
        System.out.println("     _   _                         ____            _            _                   _       ");
        System.out.println("    | \\ | |_   _  _____   _____   |  _ \\ __ _ _ __| |_ ___  ___(_)_ __   __ _ _ __ | |_ ___ ");
        System.out.println("    |  \\| | | | |/ _ \\ \\ / / _ \\  | |_) / _` | '__| __/ _ \\__/ | '_ \\ / _` | '_ \\| __/ _ \\");
        System.out.println("    | |\\  | |_| | (_) \\ V / (_) | |  __/ (_| | |  | ||  __/ (__| | |_) | (_| | | | | ||  __/");
        System.out.println("    |_| \\_|\\__,_|\\___/ \\_/ \\___/  |_|   \\__,_|_|   \\__\\___|___|_| .__/ \\__,_|_| |_|\\__\\___|");
        System.out.println("                                                             |_|                         ");
        System.out.println("*****************************************************************************************************************");
    }

    public static void showSubscriptionBanner(){
        System.out.println("**********************************************************************************************");
        System.out.println("        ___               _     _                     ____               _ ");
        System.out.println("        |_ _|___  ___ _ __(_)___(_) ___  _ __   ___   / ___|___  _ __ ___(_)");
        System.out.println("         | |/ __|/ __| '__| |_  / |/ _ \\| '_ \\ / _ \\ | |   / _ \\| '__/ __| |");
        System.out.println("         | |\\__ \\ (__| |  | |/ /| | (_) | | | |  __/ | |__| (_) | |  \\__ \\ |");
        System.out.println("        |___|___/\\___|_|  |_/___|_|\\___/|_| |_|\\___|  \\____\\___/|_|  |___/_|");
        System.out.println("                                                                                             ");
        System.out.println("**********************************************************************************************");
    }




    public static void showDaysLesson(){
        System.out.println("1) Lunedì");
        System.out.println("2) Martedì");
        System.out.println("3) Mercoledì");
        System.out.println("4) Giovedì");
        System.out.println("5) Venerdì");
        System.out.println("6) Sabato");
        System.out.println("7) Domenica");
    }

    public static void showContacts(){
        System.out.println("1) Email");
        System.out.println("2) Telefono");
        System.out.println("3) Cellulare");
        System.out.println("4) Fine");
    }

    public static void showMessage(String message){
        System.out.print(message);
    }

    public static int showErrorMessage(String message){
        System.out.println(message);

        int choice;
        while(true){

            System.out.print("Digitare 1 se si vuole rieffettuare l'azione, altrimenti 0: ");
            choice = Input.readInt();
            if(choice == 0 || choice == 1){
                break;
            }
            System.out.println("Opzione non valida");

        }

        return choice;
    }



}
