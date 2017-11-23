package com.makemytrip.exceptions;

/**
 * Classified intent not found right exception.
 */

public class IntentCheckFailureException extends Exception {
    public IntentCheckFailureException(String message) {
        super(message);
    }
}
