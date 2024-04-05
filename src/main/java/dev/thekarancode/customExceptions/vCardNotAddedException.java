package dev.thekarancode.customExceptions;

public class vCardNotAddedException extends RuntimeException {
    public vCardNotAddedException(String message) {
        super(message);
    }
}
