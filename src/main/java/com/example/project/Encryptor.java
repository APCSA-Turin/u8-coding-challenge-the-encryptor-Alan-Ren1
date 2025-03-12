
package com.example.project;

import java.util.Arrays;

public class Encryptor {

    /**
     * Determines the number of columns required for the 2D array based on the message length and rows.
     *
     * @param messageLen The length of the message.
     * @param rows       The number of rows in the 2D array.
     * @return The number of columns required.
     */
    public static int determineColumns(int messageLen, int rows) {
        if (messageLen == 0) {
            return 1; 
        }
        return (int) Math.ceil((double) messageLen / rows); 
    }


    public static String[][] generateEncryptArray(String message, int rows) {
        int messageLen = message.length();
        int columns = determineColumns(messageLen, rows);

        String[][] encryptArray = new String[rows][columns];
        for (String[] row : encryptArray) {
            Arrays.fill(row, "=");
        }


        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (index < messageLen) {
                    encryptArray[row][col] = String.valueOf(message.charAt(index));
                    index++;
                } else {
                    encryptArray[row][col] = "=";
                }
            }
        }

        return encryptArray;
    }

    public static String encryptMessage(String message, int rows) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        int messageLen = message.length();
        int columns = determineColumns(messageLen, rows);

  
        char[][] encryptArray = new char[rows][columns];
        for (char[] row : encryptArray) {
            Arrays.fill(row, '=');
        }

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (index < messageLen) {
                    encryptArray[row][col] = message.charAt(index);
                    index++;
                } else {
                    encryptArray[row][col] = '=';
                }
            }
        }


        StringBuilder encryptedMessage = new StringBuilder();
        for (int col = columns - 1; col >= 0; col--) {
            for (int row = 0; row < rows; row++) {
                encryptedMessage.append(encryptArray[row][col]);
            }
        }

        return encryptedMessage.toString();
    }

    
    public static String decryptMessage(String encryptedMessage, int rows) {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return "";
        }

        int messageLen = encryptedMessage.length();
        int columns = messageLen / rows;

        char[][] decryptArray = new char[rows][columns];
        int index = 0;

        for (int col = columns - 1; col >= 0; col--) {
            for (int row = 0; row < rows; row++) {
                if (index < messageLen) {
                    decryptArray[row][col] = encryptedMessage.charAt(index);
                    index++;
                }
            }
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (decryptArray[row][col] != '=') {
                    decryptedMessage.append(decryptArray[row][col]);
                }
            }
        }

        return decryptedMessage.toString();
    }


    public static void main(String[] args) {
        System.out.println(encryptMessage("I finally finished the coding challenge", 3));
    }
}
