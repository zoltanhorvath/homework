package hu.horvathzoltan.configuration;

import hu.horvathzoltan.exceptionmapper.GeneralExceptionMapper;
import hu.horvathzoltan.exceptionmapper.InventoryExceptionMapper;
import hu.horvathzoltan.exceptionmapper.ValidationExceptionMapper;
import hu.horvathzoltan.rest.CartRESTService;
import hu.horvathzoltan.rest.InventoryRESTService;
import hu.horvathzoltan.rest.UserRESTService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("webresources")
public class ApplicationConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CartRESTService.class);
        resources.add(InventoryRESTService.class);
        resources.add(UserRESTService.class);
        resources.add(GeneralExceptionMapper.class);
        resources.add(InventoryExceptionMapper.class);
        resources.add(ValidationExceptionMapper.class);

    }

}
