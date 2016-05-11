package hu.horvathzoltan.facade;

import hu.horvathzoltan.entity.Address;
import hu.horvathzoltan.entity.Machine;
import hu.horvathzoltan.entity.Park;
import hu.horvathzoltan.entity.Visitor;
import hu.horvathzoltan.exception.NotEnoughMoneyException;
import hu.horvathzoltan.exception.NotEnoughSpaceException;
import hu.horvathzoltan.exception.VisitorIsNotInParkException;
import hu.horvathzoltan.exception.VisitorIsNotRidingMachineException;
import hu.horvathzoltan.exception.VisitorUnderAgeException;
import hu.horvathzoltan.state.VisitorState;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ParkFacade extends AbstractFacade<Park> {

    @Inject
    VisitorFacade visitorFacade;

    @Inject
    MachineFacade machineFacade;

    public ParkFacade() {
        super(Park.class);
    }

    public Machine addMachineToPark(Long parkID, Machine machine) {

        Park park = findById(parkID);

        if (park.getCapital() >= machine.getPrice() && park.getParkSpace() >= machine.getMachineSize()) {
            park.addMachine(machine);
            park.setCapital(park.getCapital() - machine.getPrice());
            park.setParkSpace(park.getParkSpace() - machine.getMachineSize());
            machine.setPark(park);
            merge(park);
            return machine;

        } else if (park.getCapital() < machine.getPrice()) {
            throw new NotEnoughMoneyException();

        } else if (park.getParkSpace() < machine.getMachineSize()) {
            throw new NotEnoughSpaceException();
        }
        return null;
    }

    public Machine removeMachineFromPark(Long parkId, Long machineId) {
        Park park = findById(parkId);

        Machine machine = park.getMachineById(machineId);
        if (null != machine) {
            if (machine.getVisitors().isEmpty()) {
                park.setCapital(park.getCapital() + machine.getPrice());
                park.setParkSpace(park.getParkSpace() + machine.getMachineSize());
                merge(park);
                machineFacade.remove(machine);
            } else {
                throw new RuntimeException("Machine is not empty.");
            }
        }
        return machine;
    }

    public Visitor visitorEnters(Visitor visitor, Long parkId) {
        Park park = findById(parkId);

        if (visitor.getMoney() >= park.getTicketPrice()) {
            int ticketPrice = park.getTicketPrice();

            park.setCapital(park.getCapital() + ticketPrice);
            park.addVisitor(visitor);

            visitor.setMoney(visitor.getMoney() - ticketPrice);
            visitor.setPark(park);

            visitorFacade.merge(visitor);
            merge(park);

        } else {
            throw new NotEnoughMoneyException();
        }

        return visitor;
    }

    public void visitorRidesMachine(Long visitorId, Long machineId, Long parkId) {
        Visitor visitor = visitorFacade.findById(visitorId);
        Park park = findById(parkId);
        Machine machine = machineFacade.findById(machineId);

        if (park.getVisitors().contains(visitor)) {
            if (visitor.getAge() >= machine.getAgeLimit() 
                    && visitor.getMoney() >= machine.getTicketPrice() 
                    && machine.getCapacity()> machine.getVisitors().size()) {
                
                int machineTicketPrice = machine.getTicketPrice();

                visitor.setMoney(visitor.getMoney() - machineTicketPrice);
                visitor.setState(VisitorState.ON_MACHINE);

                park.setCapital(park.getCapital() + machineTicketPrice);

                visitor.setMachine(machine);
                machine.addVisitor(visitor);

                visitorFacade.merge(visitor);
                merge(park);

            } else if (visitor.getMoney() < machine.getTicketPrice()) {
                throw new NotEnoughMoneyException();
            } else if (visitor.getAge() < machine.getAgeLimit()) {
                throw new VisitorUnderAgeException();
            }
        } else {
            throw new VisitorIsNotInParkException();
        }
    }

    public void visitorLeavesMachine(Long visitorId, Long machineId, Long parkId) {
        Visitor visitor = visitorFacade.findById(visitorId);
        Park park = findById(parkId);
        Machine machine = machineFacade.findById(machineId);

        if (park.getVisitors().contains(visitor)) {
            if (machine.getVisitors().contains(visitor)) {
                visitor.setState(VisitorState.REST);
                visitor.setMachine(null);
                machine.getVisitors().remove(visitor);

                machineFacade.merge(machine);
                visitorFacade.merge(visitor);
            } else {
                throw new VisitorIsNotRidingMachineException();
            }
        } else {
            throw new VisitorIsNotInParkException();
        }
    }

    public void visitorLeavesPark(Long visitorId, Long parkId) {
        Visitor visitor = visitorFacade.findById(visitorId);
        Park park = findById(parkId);

        if (park.getVisitors().contains(visitor)) {
            if (visitor.getMachine() != null) {
                Machine machine = machineFacade.findById(visitor.getMachine().getId());
                machine.getVisitors().remove(visitor);
                visitor.setMachine(null);
                visitor.setState(VisitorState.REST);

                visitorFacade.merge(visitor);
                park.getVisitors().remove(visitor);
                merge(park);
            } else {
                visitor.setMachine(null);
                visitor.setState(VisitorState.REST);
                visitorFacade.merge(visitor);
                park.getVisitors().remove(visitor);
                merge(park);
            }
        } else {
            throw new VisitorIsNotInParkException();
        }
    }

    public List<Machine> getMachines(Long parkId) {
        Park park = findById(parkId);
        return park.getMachines();
    }

    public int countRestingVisitorsInPark(Long parkId) {
        Park park = findById(parkId);
        int sum = 0;

        for (Visitor visitor : park.getVisitors()) {
            if (visitor.getState() == VisitorState.REST) {
                sum++;
            }
        }
        return sum;
    }

    public Address getAddress(Long parkId) {
        Park park = findById(parkId);
        return park.getAddress();
    }
}
