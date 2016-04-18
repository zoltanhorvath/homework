package hu.horvathzoltan.exceptionmapper;

import hu.horvathzoltan.dto.ErrorDTO;
import hu.horvathzoltan.exception.InventoryException;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class InventoryExceptionMapper implements ExceptionMapper<InventoryException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(InventoryException exception) {
        logger.log(Level.WARNING, "Inventory Exception", exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
