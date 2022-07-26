package com.sdapps.f1racecalendar;

public class DriverDataObj {
    private String driverFirstName;
    private String driverLastName;

    public DriverDataObj() {
    }

    public DriverDataObj(String driverFirstName, String driverLastName) {
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }
}
