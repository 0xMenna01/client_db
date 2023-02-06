package utils;

import java.lang.reflect.Field;
import java.util.List;

public class TablePrinter<P> {
    public void printTable(List<P> objects, String[] columnNames) {
        if (objects.isEmpty() || columnNames.length == 0) {
            System.out.println("Errore: Tabella vuota");
            return;
        }
        // Get the number of columns from the first object
        int numColumns = objects.get(0).getClass().getDeclaredFields().length;
        // Create a 2D array to store the data
        String[][] data = new String[objects.size() + 1][numColumns];
        data[0] = columnNames;

        // Fill the data array with the attribute values
        for (int i = 0; i < objects.size(); i++) {
            Field[] fields = objects.get(i).getClass().getDeclaredFields();
            for (int j = 0; j < numColumns; j++) {
                fields[j].setAccessible(true);
                try {
                    data[i + 1][j] = fields[j].get(objects.get(i)).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Find the maximum length of each column
        int[] columnWidths = new int[numColumns];
        for (int i = 0; i < numColumns; i++) {
            columnWidths[i] = columnNames[i].length();
            for (int j = 1; j < objects.size() + 1; j++) {
                columnWidths[i] = Math.max(columnWidths[i], data[j][i].length());
            }
        }

        // Print the table
        for (int i = 0; i < objects.size() + 1; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print("| " + String.format("%-" + columnWidths[j] + "s", data[i][j]) + " ");
            }
            System.out.println("|");
            if (i == 0) {
                for (int j = 0; j < numColumns; j++) {
                    System.out.print("+" + String.format("%-" + (columnWidths[j] + 2) + "s", "").replace(" ", "-"));
                }
                System.out.println("+");
            }
        }
    }
}

