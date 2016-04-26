package hu.horvathzoltan.worker;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;


@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/dzsobKju"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        }
)
public class SlowWorkerMDB extends WorkerMDB implements MessageListener {

    public SlowWorkerMDB() {
        multiplier = 2.0;
    }
}
