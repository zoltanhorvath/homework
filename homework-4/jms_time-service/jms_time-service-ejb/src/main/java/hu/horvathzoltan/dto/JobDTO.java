package hu.horvathzoltan.dto;

import java.io.Serializable;
import java.util.Random;

public class JobDTO implements Serializable {
    private int serialNumber;
    private int estimatedTime;

    public JobDTO() {
        //No-arg constructor
    }

    public JobDTO(int serialNumber) {
        this.serialNumber = serialNumber;
        this.estimatedTime = new Random().nextInt(5) + 1;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "serialNumber=" + serialNumber +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
