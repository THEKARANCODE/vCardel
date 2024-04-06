package dev.thekarancode.utilityClasses;

import dev.thekarancode.customExceptions.UnsupportedCharacterException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Handyman {
    private static final Set<Character> supportedCharacters = new HashSet<>();

    static {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            supportedCharacters.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            supportedCharacters.add(ch);
        }
        for (char ch = '0'; ch <= '9'; ch++) {
            supportedCharacters.add(ch);
        }
        supportedCharacters.add(' ');
        supportedCharacters.add('~');
        supportedCharacters.add('!');
        supportedCharacters.add('@');
        supportedCharacters.add('#');
        supportedCharacters.add('$');
        supportedCharacters.add('%');
        supportedCharacters.add('^');
        supportedCharacters.add('&');
        supportedCharacters.add('*');
        supportedCharacters.add('(');
        supportedCharacters.add(')');
        supportedCharacters.add('_');
        supportedCharacters.add('+');
        supportedCharacters.add('-');
        supportedCharacters.add('=');
        supportedCharacters.add('`');
        supportedCharacters.add('{');
        supportedCharacters.add('}');
        supportedCharacters.add('|');
        supportedCharacters.add(';');
        supportedCharacters.add(':');
        supportedCharacters.add('\'');
        supportedCharacters.add('/');
        supportedCharacters.add('?');
        supportedCharacters.add('<');
        supportedCharacters.add('>');
        supportedCharacters.add(',');
        supportedCharacters.add('.');
        supportedCharacters.add('[');
        supportedCharacters.add(']');
        supportedCharacters.add('\\');
        supportedCharacters.add('"');
    }

    public static boolean isValidDate(int dayOfMonth, int month, int year) {
        try {
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean isValidDate(int dayOfMonth, int month) {
        int YYYY = 2000; /*Chose 2000 for leap year; simplifies validation to check day and month, including February 29.*/
        try {
            LocalDate date = LocalDate.of(YYYY, month, dayOfMonth);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static String foldLine(String inputString, int maxLength) throws UnsupportedCharacterException {
        if (hasUnsupportedChar(inputString)) {
            String unsupportedCharacters = getUnsupportedChars(inputString);
            throw new UnsupportedCharacterException(unsupportedCharacters.length() + " unsupported character" + (unsupportedCharacters.length() > 1 ? "s are " : " is ") + "present in the input string : { " + unsupportedCharacters + " }.");
        }
        if (inputString.length() <= maxLength) {
            return inputString;
        }

        StringBuilder foldedInputString = new StringBuilder();

        int currentLineNumber = 1;
        int currentLineLength = 0;
        for (int i = 0; i < inputString.length(); i++) {
            foldedInputString.append(inputString.charAt(i));
            currentLineLength++;
            if (currentLineLength == (currentLineNumber > 1 ? maxLength - 1 : maxLength)) {
                foldedInputString.append("\r\n ");
                currentLineNumber++;
                currentLineLength = 0;
            }
        }
        return foldedInputString.toString();
    }

    public static String escapeCharacters(String inputString, String charactersToEscape) {
        StringBuilder result = new StringBuilder();
        for (char ch : inputString.toCharArray()) {
            result.append((charactersToEscape.indexOf(ch) == -1) ? ch : "\\" + ch);
        }
        return result.toString();
    }

    public static boolean hasUnsupportedChar(String inputString) {
        for (char ch : inputString.toCharArray()) {
            if (!supportedCharacters.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    public static String getUnsupportedChars(String inputString) {
        StringBuilder unsupportedCharacters = new StringBuilder();
        for (char ch : inputString.toCharArray()) {
            if (!supportedCharacters.contains(ch)) {
                unsupportedCharacters.append(ch);
            }
        }
        return unsupportedCharacters.toString();
    }

    public static String capitalizeIf(String inputString, boolean capitalize) {
        return capitalize ? inputString.toUpperCase() : inputString;
    }

    public static LocalDate toLocaleDateObj(String date) {
        LocalDate dateObj = null;
        if (!date.matches("[0-9]{2}/[0-9]{2}(/[0-9]{4})?")) {
            return dateObj;
        }

        String[] dateSegStrArray = date.split("/");
        int[] dateSegIntArray = new int[dateSegStrArray.length];

        for (int i = 0; i < dateSegIntArray.length; i++) {
            dateSegIntArray[i] = Integer.parseInt(dateSegStrArray[i]);
        }

        switch (dateSegIntArray.length) {
            case 2:
                if (isValidDate(dateSegIntArray[0], dateSegIntArray[1])) {
                    dateObj = LocalDate.of(0, dateSegIntArray[1], dateSegIntArray[0]);
                }
                break;
            case 3:
                if (isValidDate(dateSegIntArray[0], dateSegIntArray[1], dateSegIntArray[2])) {
                    dateObj = LocalDate.of(dateSegIntArray[2], dateSegIntArray[1], dateSegIntArray[0]);
                }
                break;
        }

        return dateObj;
    }

    public static boolean isValidEmailFormat(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public static boolean isValidDateFormat(String date) {
        return date.matches("[0-9]{2}/[0-9]{2}(/[0-9]{4})?");
    }
}