package hu.horvathzoltan.rest;

import hu.horvathzoltan.annotation.BeanValidation;
import hu.horvathzoltan.dto.MobileDTO;
import hu.horvathzoltan.service.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
@BeanValidation
public class InventoryRESTService {
    @Inject
    private InventoryService inventoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileDTO addMobile(MobileDTO mobileDTO) {
        return inventoryService.addMobile(mobileDTO);
    }

    @GET
    public List<MobileDTO> getMobiles() {
        return inventoryService.getMobilesList();
    }
}
