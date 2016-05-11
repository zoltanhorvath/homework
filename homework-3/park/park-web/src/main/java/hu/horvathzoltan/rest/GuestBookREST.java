package hu.horvathzoltan.rest;

import hu.horvathzoltan.facade.GuestBookFacade;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/guest_book")
public class GuestBookREST {
    
    @Inject GuestBookFacade guestBookFacade;
    
    @GET
    @Path("/park/{park_id}/visitor/{visitor_id}")
    public List<String> getEntries(@PathParam("park_id") Long parkId, @PathParam("visitor_id") Long visitorId){
        return guestBookFacade.getGuestBookEntries(visitorId, parkId);
    }
}
