package hu.horvathzoltan.dto;

import java.io.Serializable;

import hu.horvathzoltan.type.JobStateType;

public class MessageDTO implements Serializable {

    private int serialNumber;
    private JobStateType jobState;

    public MessageDTO(int serialNumber, JobStateType jobState) {
        this.serialNumber = serialNumber;
        this.jobState = jobState;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public JobStateType getJobState() {
        return jobState;
    }

    public void setJobState(JobStateType jobState) {
        this.jobState = jobState;
    }
}
