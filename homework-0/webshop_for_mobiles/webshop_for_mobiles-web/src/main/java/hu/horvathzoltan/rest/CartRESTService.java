package hu.horvathzoltan.rest;

import hu.horvathzoltan.annotation.BeanValidation;
import hu.horvathzoltan.dto.MobileDTO;
import hu.horvathzoltan.service.CartService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@BeanValidation
public class CartRESTService {

    @Inject
    private CartService cartService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MobileDTO addToCart(MobileDTO mobileDTO) {
        return cartService.addToCart(mobileDTO);
    }

    @POST
    @Path("/checkout")
    public void checkout() {
        cartService.checkout();
    }
}