package hu.horvathzoltan.service;

import hu.horvathzoltan.dto.MobileDTO;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateful
@LocalBean
public class CartService {

    @Inject
    private InventoryService inventoryService;

    private final List<MobileDTO> cart = new ArrayList<>();

    public List<MobileDTO> getCart() {
        return cart;
    }

    public MobileDTO addToCart(MobileDTO mobileDTO) {
        cart.add(mobileDTO);
        return cart.get(cart.size() - 1);
    }

    @Remove
    public void checkout() {
        for (MobileDTO mobileDTO : cart) {
            inventoryService.buyMobile(mobileDTO);
        }
        
    }
}
