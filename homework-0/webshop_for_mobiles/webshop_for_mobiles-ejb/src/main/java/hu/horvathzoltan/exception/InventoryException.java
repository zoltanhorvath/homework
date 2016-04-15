package hu.horvathzoltan.exception;

public class InventoryException extends RuntimeException{
    public InventoryException() {
        super();
    }

    public InventoryException(String message) {
        super(message);
    }
}
