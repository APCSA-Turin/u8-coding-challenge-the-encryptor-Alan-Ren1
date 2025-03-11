
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
            return 1; // At least one column for an empty message
        }
        return (int) Math.ceil((double) messageLen / rows); // Round up to ensure all characters fit
    }

    /**
     * Generates a 2D array filled with message characters, padded with "=" if necessary.
     *
     * @param message The message to encrypt.
     * @param rows    The number of rows in the 2D array.
     * @return A 2D array containing the message characters and padding.
     */
    public static String[][] generateEncryptArray(String message, int rows) {
        int messageLen = message.length();
        int columns = determineColumns(messageLen, rows);

        String[][] encryptArray = new String[rows][columns];
        for (String[] row : encryptArray) {
            Arrays.fill(row, "=");
        }

        // Fill the 2D array row-wise
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

    /**
     * Encrypts the message by reading the 2D array column-wise.
     */
    public static String encryptMessage(String message, int rows) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        int messageLen = message.length();
        int columns = determineColumns(messageLen, rows);

        // Step 1: Fill the 2D array row-wise
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

        // Step 2: Read the 2D array column-wise
        StringBuilder encryptedMessage = new StringBuilder();
        for (int col = columns - 1; col >= 0; col--) {
            for (int row = 0; row < rows; row++) {
                encryptedMessage.append(encryptArray[row][col]);
            }
        }

        return encryptedMessage.toString();
    }

    /**
     * Decrypts the message by reading the 2D array row-wise.
     */
    public static String decryptMessage(String encryptedMessage, int rows) {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return "";
        }

        int messageLen = encryptedMessage.length();
        int columns = messageLen / rows;

        // Step 1: Fill the 2D array column-wise
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

        // Step 2: Read the array row-wise (ignore '=' padding)
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
}
