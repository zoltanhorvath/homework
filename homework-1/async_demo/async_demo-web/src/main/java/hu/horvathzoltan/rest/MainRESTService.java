package hu.horvathzoltan.rest;

import hu.horvathzoltan.async.AsyncClass;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("/test")
public class MainRESTService {

    private static final Logger LOGGER = Logger.getLogger(MainRESTService.class.getName());
    @Inject
    private AsyncClass asyncClass;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String callAsyncMethod() {

        LOGGER.log(Level.INFO, "REST Service started in thread : ");

        asyncClass.asyncMethod();

        LOGGER.log(Level.INFO, "REST Service finished in thread : ");

        return "Asynchronous method called.";
    }

    @GET
    @Path("/future/{seconds}")
    @Produces(MediaType.TEXT_PLAIN)
    public void callAsyncMethodFuture(@PathParam("seconds") int seconds) {
        Future<String> result = asyncClass.asyncMethodFuture(); //Takes 3 seconds to complete.

        try {
            String resultStr = result.get(seconds, TimeUnit.SECONDS);
            LOGGER.log(Level.INFO, resultStr);

        } catch (TimeoutException e) {
            LOGGER.log(Level.INFO, "asyncMethodFuture in not yet done.", e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException", e);
        } catch (ExecutionException e) {
            LOGGER.log(Level.SEVERE, "ExecutionException", e);
        }
    }

}
