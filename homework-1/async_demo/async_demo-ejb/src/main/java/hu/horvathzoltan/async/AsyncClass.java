package hu.horvathzoltan.async;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AsyncClass {
    private static final Logger LOGGER = Logger.getLogger(AsyncClass.class.getName());

    @Asynchronous
    public void asyncMethod() {
        LOGGER.log(Level.INFO, "Begin asynchronous method in thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException", e);
        }
        LOGGER.log(Level.INFO, "End asynchronous method in thread: " + Thread.currentThread().getName());
    }

    @Asynchronous
    public Future<String> asyncMethodFuture() {
        LOGGER.log(Level.INFO, "Begin asynchronous method in thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException", e);
        }
        LOGGER.log(Level.INFO, "End asynchronous method in thread: " + Thread.currentThread().getName());
        return new AsyncResult<>("asyncMethodFuture result.");
    }
}