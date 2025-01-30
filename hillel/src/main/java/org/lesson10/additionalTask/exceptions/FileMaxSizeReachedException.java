package org.lesson10.additionalTask.exceptions;

public class FileMaxSizeReachedException extends RuntimeException {
    public FileMaxSizeReachedException(String message) {
        super(message);
    }
}
