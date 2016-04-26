package hu.horvathzoltan.bean;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import hu.horvathzoltan.dto.MessageDTO;
import hu.horvathzoltan.type.ResultType;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/dzsobTopik"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        }
)
public class StatisticsBean implements MessageListener {

    @Inject
    private Statistics statistics;
    private static final int TIMEOUT = 5000;

    @Override
    public void onMessage(Message message) {
        try {
            MessageDTO messageDTO = message.getBody(MessageDTO.class);

            switch (messageDTO.getJobState()) {
                case STARTED:
                    statistics.addJob(messageDTO.getSerialNumber(), message.getJMSTimestamp());
                    break;
                case FINISHED:
                    saveFinshedJobInfo(messageDTO, message);
                    break;
                default:
            }

        } catch (JMSException e) {
            Logger.getLogger(StatisticsBean.class.getName()).log(Level.SEVERE, (Supplier<String>) e);
        }
    }

    private void saveFinshedJobInfo(MessageDTO messageDTO, Message message) throws JMSException {
        Integer serialNumber = messageDTO.getSerialNumber();
        Long startingTime = statistics.getStartingTime(serialNumber);
        Long finishingTime = message.getJMSTimestamp();
        ResultType result = ResultType.FAILED;

        if (finishingTime - startingTime < TIMEOUT) {
            result = ResultType.SUCCEEDED;
        }

        statistics.addResult(serialNumber, result);
    }
}
