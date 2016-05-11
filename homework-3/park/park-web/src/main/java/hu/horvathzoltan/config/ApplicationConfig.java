package hu.horvathzoltan.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.horvathzoltan.exceptionmapper.GeneralExceptionMapper.class);
        resources.add(hu.horvathzoltan.exceptionmapper.NotEnoughMoneyExceptionMapper.class);
        resources.add(hu.horvathzoltan.exceptionmapper.NotEnoughSpaceExceptionMapper.class);
        resources.add(hu.horvathzoltan.rest.GuestBookREST.class);
        resources.add(hu.horvathzoltan.rest.MachineREST.class);
        resources.add(hu.horvathzoltan.rest.ParkREST.class);
    }

}
