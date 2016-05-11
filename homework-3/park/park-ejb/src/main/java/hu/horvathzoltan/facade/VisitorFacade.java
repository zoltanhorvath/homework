package hu.horvathzoltan.facade;

import hu.horvathzoltan.entity.Visitor;
import javax.ejb.Stateless;

@Stateless
public class VisitorFacade extends AbstractFacade<Visitor> {

    public VisitorFacade() {
        super(Visitor.class);
    }

    @Override
    public void remove(Visitor visitor) {
        visitor.setActive(false);
    }
    
}
