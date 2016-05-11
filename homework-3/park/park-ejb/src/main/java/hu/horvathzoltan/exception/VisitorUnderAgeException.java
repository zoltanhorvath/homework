package hu.horvathzoltan.exception;

public class VisitorUnderAgeException extends RuntimeException {

    public VisitorUnderAgeException(String msg) {
        super(msg);
    }

    public VisitorUnderAgeException() {
        super();
    }
}
