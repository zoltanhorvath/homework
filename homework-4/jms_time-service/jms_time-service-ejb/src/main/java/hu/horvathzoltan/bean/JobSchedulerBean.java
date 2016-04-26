package hu.horvathzoltan.bean;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

import hu.horvathzoltan.dto.JobDTO;
import hu.horvathzoltan.dto.MessageDTO;
import hu.horvathzoltan.type.JobStateType;

@Stateless
public class JobSchedulerBean {

    private static final int NUMBER_OF_JOBS = 10;
    private static AtomicInteger serialNumbel = new AtomicInteger(0);

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/dzsobKju")
    private Queue queue;

    @Resource(mappedName = "java:/jms/dzsobTopik")
    private Topic topic;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void createJobs() {
        for (int i = 0; i < NUMBER_OF_JOBS; i++) {
            context.createProducer().send(queue, new JobDTO(serialNumbel.get()));
            context.createProducer().send(topic, new MessageDTO(serialNumbel.get(), JobStateType.STARTED));
            serialNumbel.getAndIncrement();
        }

    }
}
