package hu.horvathzoltan.worker;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/dzsobKju"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        }
)

public class FastWorker extends WorkerMDB {

    public FastWorker() {
        multiplier = 0.5;
    }
}
