package se.kth.iv1201.retail.integration;

/**
 * Is thrown when an operation in <code>ItemInventory</code> goes wrong.
 */
public class ItemInventoryException extends RuntimeException{

    /**
     * Creates a new instance representing the condition described in
     * the message <code>msg</code>.
     *
     * @param msg A message describing what went wrong.
     */
    ItemInventoryException(String msg){
        super(msg);
    }

    /**
     * Creates a new instance with the root cause and message specified.
     *
     * @param msg The message.
     * @param exc The root cause <code>Exception</code>.
     */
    ItemInventoryException(String msg, Exception exc){
        super(msg,exc);
    }
}
