package hu.horvathzoltan.exception;

public class VisitorIsNotInParkException extends RuntimeException {

    public VisitorIsNotInParkException(String msg) {
        super(msg);
    }

    public VisitorIsNotInParkException() {
        super();
    }
}
