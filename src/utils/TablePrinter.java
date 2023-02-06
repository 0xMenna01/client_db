package utils;

import java.util.HashMap;
import java.util.List;

public class TablePrinter {
    public static void printTable(List<HashMap<String, String>> maps, List<String> columnNames) {
        // Calcola la lunghezza massima di ogni colonna
        int[] columnLengths = new int[columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
            columnLengths[i] = columnNames.get(i).length();
            for (HashMap<String, String> map : maps) {
                for (String value : map.values()) {
                    columnLengths[i] = Math.max(columnLengths[i], value.length());
                }
            }
        }

        // Stampa la riga di intestazione
        for (int i = 0; i < columnNames.size(); i++) {
            System.out.printf("%-" + (columnLengths[i] + 2) + "s", columnNames.get(i));
        }
        System.out.println();

        // Stampa la riga di sottolineatura
        for (int i = 0; i < columnNames.size(); i++) {
            for (int j = 0; j < columnLengths[i] + 2; j++) {
                System.out.print("-");
            }
        }
        System.out.println();

        // Stampa le righe dei dati
        for (HashMap<String, String> map : maps) {
            for (int i = 0; i < columnNames.size(); i++) {
                System.out.printf("%-" + (columnLengths[i] + 2) + "s", map.get(columnNames.get(i)));
            }
            System.out.println();
        }
        System.out.println();
    }
}

