package hu.horvathzoltan.rest;

import hu.horvathzoltan.entity.Machine;
import hu.horvathzoltan.entity.Visitor;
import hu.horvathzoltan.facade.MachineFacade;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/machine")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class MachineREST {

    @Inject
    MachineFacade machineFacade;

    @GET
    @Path("/")
    public List<Machine> getMachines() {
        return machineFacade.findAll();
    }

    @GET
    @Path("/{machine_id}")
    public Machine getMachine(@PathParam("machine_id") Long machineId) {
        return machineFacade.findById(machineId);
    }

    @PUT
    @Path("/update_machine")
    public Machine updateMachine(Machine machine) {
        return machineFacade.merge(machine);

    }

    @POST
    @Path("/add_machine")
    public Machine addMachine(Machine machine) {
        return machineFacade.persist(machine);
    }

    @DELETE
    @Path("/{machine_id}")
    public void deleteMachine(@PathParam("machine_id") Long machineId) {
        Machine machine = machineFacade.findById(machineId);
        machineFacade.remove(machine);
    }

    @GET
    @Path("/{machine_id}/visitors")
    public List<Visitor> getVisitors(@PathParam("machine_id") Long machineId) {
        return machineFacade.getVisitors(machineId);

    }
   
}
