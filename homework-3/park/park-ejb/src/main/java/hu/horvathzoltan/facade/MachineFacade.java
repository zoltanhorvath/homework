package hu.horvathzoltan.facade;

import hu.horvathzoltan.entity.Machine;
import hu.horvathzoltan.entity.Visitor;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class MachineFacade extends AbstractFacade<Machine> {

    public MachineFacade() {
        super(Machine.class);
    }

    public List<Visitor> getVisitors(Long machineId) {
        Machine machine = findById(machineId);
        return machine.getVisitors();
    }
}
