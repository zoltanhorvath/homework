package hu.horvathzoltan.rest;

import hu.horvathzoltan.entity.Address;
import hu.horvathzoltan.entity.Machine;
import hu.horvathzoltan.entity.Park;
import hu.horvathzoltan.entity.Visitor;
import hu.horvathzoltan.facade.ParkFacade;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

@Path("/park")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class ParkREST {

    @Inject
    ParkFacade parkFacade;

    @GET
    @Path("/")
    public List<Park> getParks() {
        return parkFacade.findAll();
    }

    @GET
    @Path("/{park_id}")
    public Park getPark(@PathParam("park_id") Long parkId) {
        return parkFacade.findById(parkId);
    }

    @PUT
    @Path("/update_park}")
    public Park updatePark(Park park) {
        return parkFacade.merge(park);
    }

    @DELETE
    @Path("/{park_id}")
    public void deletePark(@PathParam("park_id") Long parkId) {
        parkFacade.remove(parkFacade.findById(parkId));

    }

    @POST
    @Path("/add_park")
    public Park addPark(Park park) {
        return parkFacade.persist(park);
    }

    @POST
    @Path("/{park_id}/add_machine")
    public Machine addMachine(@PathParam("park_id") Long parkId, Machine machine) {
        return parkFacade.addMachineToPark(parkId, machine);

    }

    @POST
    @Path("/{park_id}/add_visitor")
    public Visitor visitorEntersPark(@PathParam("park_id") Long parkId, Visitor visitor) {
        return parkFacade.visitorEnters(visitor, parkId);
    }

    @DELETE
    @Path("/{park_id}/visitor/{visitor_id}")
    public void visitorLeaves(@PathParam("park_id") Long parkId,
            @PathParam("visitor_id") Long visitorId) {
        parkFacade.visitorLeavesPark(visitorId, parkId);
    }

    @POST
    @Path("/{park_id}/machine/{machine_id}/visitor/{visitor_id}")
    public void addVisitorToMachine(@PathParam("park_id") Long parkId,
            @PathParam("machine_id") Long machineId, @PathParam("visitor_id") Long visitorId) {
        parkFacade.visitorRidesMachine(visitorId, machineId, parkId);
    }

    @DELETE
    @Path("/{park_id}/machine/{machine_id}/visitor/{visitor_id}")
    public void removeVisitorFromMachine(@PathParam("park_id") Long parkId,
            @PathParam("machine_id") Long machineId, @PathParam("visitor_id") Long visitorId) {
        parkFacade.visitorLeavesMachine(visitorId, machineId, parkId);
    }

    @DELETE
    @Path("/{park_id}/remove_machine/{machine_id}")
    public Machine removeMachine(@PathParam("park_id") Long parkId,
            @PathParam("machine_id") Long machineId) {
        return parkFacade.removeMachineFromPark(parkId, machineId);
    }

    @GET
    @Path("/{park_id}/get_address")
    public Address getAddress(@PathParam("park_id") Long parkId) {
        return parkFacade.getAddress(parkId);
    }

    @GET
    @Path("/{park_id}/get_machines")
    public List<Machine> getMachines(@PathParam("park_id") Long parkId) {
        return parkFacade.getMachines(parkId);
    }
    @GET
    @Path("/{park_id}/resting_visitors")
    public int countRestingVisitors(@PathParam("park_id") Long parkId){
        return parkFacade.countRestingVisitorsInPark(parkId);
    }

}
