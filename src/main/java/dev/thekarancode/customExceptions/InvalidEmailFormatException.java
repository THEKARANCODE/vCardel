package dev.thekarancode.customExceptions;

public class InvalidEmailFormatException extends Exception {
    private String invalidEmail;

    public InvalidEmailFormatException(String message, String invalidEmail) {
        super(message);
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }
}
