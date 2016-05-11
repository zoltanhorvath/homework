
package hu.horvathzoltan.exception;


public class NotEnoughSpaceException extends RuntimeException {

    public NotEnoughSpaceException(String msg) {
        super(msg);
    }

    public NotEnoughSpaceException() {
        super();
    }
}
