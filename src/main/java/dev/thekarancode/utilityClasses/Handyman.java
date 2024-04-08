package dev.thekarancode.utilityClasses;

import dev.thekarancode.customExceptions.InvalidDateException;
import dev.thekarancode.customExceptions.InvalidDateFormatException;
import dev.thekarancode.customExceptions.InvalidEmailFormatException;
import dev.thekarancode.customExceptions.UnsupportedCharacterException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Handyman {

    /*Date Validation Methodsd*/
    private static final Pattern DATE_PATTERN = Pattern.compile("[0-9]{2}/[0-9]{2}(/[0-9]{4})?");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Set<Character> supportedCharacters = new HashSet<>();

    public static boolean isValidDate(int dayOfMonth, int month, int year) {
        try {
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static boolean isValidDate(int dayOfMonth, int month) {
        int year = 2000; /*Chose 2000 for leap year; simplifies validation to check day and month, including February 29.*/
        try {
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static boolean isValidDateFormat(String dateString) {
        return DATE_PATTERN.matcher(dateString).matches();
    }

    public static void dateValidator(String dateString) throws InvalidDateFormatException, InvalidDateException {

        if (dateString == null) {
            throw new InvalidDateFormatException("Date string cannot be null or empty. Expected formats: DD/MM or DD/MM/YYYY.");
        }

        if (dateString.isBlank()) {
            return;
        }

        if (!isValidDateFormat(dateString)) {
            throw new InvalidDateFormatException("Invalid date format: " + dateString + ". Expected formats: DD/MM or DD/MM/YYYY.");
        }

        String[] dateStringElements = dateString.split("/");

        int dayOfMonth = Integer.parseInt(dateStringElements[0]);
        int month = Integer.parseInt(dateStringElements[1]);
        int year;

        if (dateStringElements.length == 3) {
            year = Integer.parseInt(dateStringElements[2]);
            dateValidator(dayOfMonth, month, year);
        } else {
            dateValidator(dayOfMonth, month);
        }
    }

    public static void dateValidator(int dayOfMonth, int month, int year) throws InvalidDateException {
        try {
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
        } catch (DateTimeException e) {
            throw new InvalidDateException("Invalid Date: " + dayOfMonth + "/" + month + "/" + year + ", Explanation: " + e.getMessage() + ".");
        }
    }

    public static void dateValidator(int dayOfMonth, int month) throws InvalidDateException {
        int year = 2000; /*Chose 2000 for leap year; simplifies validation to check day and month, including February 29.*/
        try {
            LocalDate date = LocalDate.of(2000, month, dayOfMonth);
        } catch (DateTimeException e) {
            throw new InvalidDateException("Invalid Date: " + dayOfMonth + "/" + month + ", Explanation: " + e.getMessage() + ".");
        }
    }

    public static LocalDate parseLocalDate(String dateString) throws InvalidDateFormatException, InvalidDateException {

        dateValidator(dateString);

        String[] dateStringElements = dateString.split("/");

        int dayOfMonth = Integer.parseInt(dateStringElements[0]);
        int month = Integer.parseInt(dateStringElements[1]);
        int year = dateStringElements.length == 3 ? Integer.parseInt(dateStringElements[0]) : 2000;

        return LocalDate.of(year, month, dayOfMonth);
    }


    /*Email Validation Methods*/
    public static boolean isValidEmailFormat(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static void emailValidator(String email) throws InvalidEmailFormatException {
        if (email.isBlank()) {
            return;
        }
        if (!isValidEmailFormat(email)) {
            throw new InvalidEmailFormatException("Invalid email address format: " + email + ".", email);
        }
    }


    /*String Utility Methods*/
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

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static boolean isNullOrBlank(String[] array) {
        return array == null || Arrays.stream(array).allMatch(str -> str == null || str.isBlank());
    }


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
}