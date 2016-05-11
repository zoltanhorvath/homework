package hu.horvathzoltan.exception;

public class VisitorIsNotRidingMachineException extends RuntimeException {

    public VisitorIsNotRidingMachineException(String msg) {
        super(msg);
    }

    public VisitorIsNotRidingMachineException() {
        super();
    }
}
