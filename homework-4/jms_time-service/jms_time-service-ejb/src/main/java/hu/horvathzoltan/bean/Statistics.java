package hu.horvathzoltan.bean;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import hu.horvathzoltan.type.ResultType;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class Statistics {
    private static final Map<Integer, Long> jobs = new HashMap<>();
    private static final Map<Integer, ResultType> results = new HashMap<>();

    @Lock(LockType.WRITE)
    public void addJob(Integer serialNumber, Long startingTime) {
        jobs.put(serialNumber, startingTime);
    }

    public Long getStartingTime(Integer serialNumber) {
        return jobs.get(serialNumber);
    }

    @Lock(LockType.WRITE)
    public void addResult(Integer serialNumber, ResultType result) {
        results.put(serialNumber, result);
    }

    public Map<Integer, Long> getJobs() {
        return jobs;
    }

    public Map<Integer, ResultType> getResults() {
        return results;
    }

}
