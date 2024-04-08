package dev.thekarancode.sandbox;


import dev.thekarancode.customExceptions.InvalidDateException;
import dev.thekarancode.customExceptions.InvalidDateFormatException;

import static dev.thekarancode.utilityClasses.Handyman.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Sandbox {
    public static void main(String[] args) {
        Pattern DATE_PATTERN = Pattern.compile("([0-9]{2}/[0-9]{2}(/[0-9]{4})?)?");
        System.out.println(DATE_PATTERN.matcher("").matches());
    }

}
