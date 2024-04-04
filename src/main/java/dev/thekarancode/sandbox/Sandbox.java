package dev.thekarancode.sandbox;


import dev.thekarancode.logUtilityClasses.Log;
import dev.thekarancode.logUtilityClasses.LogCategory;

public class Sandbox {
    public static void main(String[] args) {
//        vCardelApplicationLogger vCardelApplicationLogger = new vCardelApplicationLogger();
        System.out.println( new Log(LogCategory.INFO, "Processing data", "Data processing started."));
    }
}
