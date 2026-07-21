package com.ticketing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shiftName;
    private String startTime;
    private String endTime;
    private Integer noOfResources;
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getShiftName() { return shiftName; }
    public void setShiftName(String shiftName) { this.shiftName = shiftName; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public Integer getNoOfResources() { return noOfResources; }
    public void setNoOfResources(Integer noOfResources) { this.noOfResources = noOfResources; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}