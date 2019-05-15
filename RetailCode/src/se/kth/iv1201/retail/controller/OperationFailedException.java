package se.kth.iv1201.retail.controller;

/**
 * Thrown when an operation fails for an unknown reason.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance with the specified message for propagation
     * and root cause.
     *
     * @param msg   The exception message.
     * @param cause The root cause exception.
     */
    public OperationFailedException(String msg, Exception cause){
        super(msg, cause);
    }
}
