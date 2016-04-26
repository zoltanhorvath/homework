package hu.horvathzoltan.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.horvathzoltan.bean.Statistics;


@Path("/")
public class MainRESTService {

    @Inject
    Statistics statistics;

    @GET
    @Path("/get-results")
    @Produces(MediaType.TEXT_PLAIN)
    public String getResults() {
        return statistics.getResults().toString();
    }
}
