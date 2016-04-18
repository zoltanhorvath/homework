package hu.horvathzoltan.service;

import hu.horvathzoltan.dto.MobileDTO;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartService implements Serializable {

    private final List<MobileDTO> cart = new ArrayList<>();

    @Inject
    private InventoryService inventoryService;

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