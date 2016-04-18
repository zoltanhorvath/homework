package hu.horvathzoltan.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class InventoryException extends RuntimeException {
    public InventoryException() {
        super();
    }

    public InventoryException(String message) {
        super(message);
    }
}