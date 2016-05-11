package hu.horvathzoltan.facade;

import hu.horvathzoltan.entity.Address;
import javax.ejb.Stateless;

@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    public AddressFacade() {
        super(Address.class);
    }

}
