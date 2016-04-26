package hu.horvathzoltan.worker;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

import hu.horvathzoltan.dto.JobDTO;
import hu.horvathzoltan.dto.MessageDTO;
import hu.horvathzoltan.type.JobStateType;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/dzsobKju"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        }
)
public class WorkerMDB implements MessageListener {

    @Inject
    JMSContext context;

    @Resource(mappedName = "java:/jms/dzsobTopik")
    Topic topic;

    double multiplier = 1.0;

    @Override
    public void onMessage(Message message) {
        try {
            JobDTO jobDTO = message.getBody(JobDTO.class);

            Thread.sleep((long) (jobDTO.getEstimatedTime() * multiplier * 1000));

            context.createProducer().send(topic, new MessageDTO(jobDTO.getSerialNumber(), JobStateType.FINISHED));

        } catch (JMSException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, this.getClass().toString(), e);

        } catch (InterruptedException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, this.getClass().toString(), e);
        }
    }
}
