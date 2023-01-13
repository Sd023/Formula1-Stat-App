package com.sdapps.f1racecalendar;

public class DriverdataBO {

    private String driverName;
    private String key;
    private String driverNumber;
    private String driverConstructor;
    private String driverPoints;
    private int driverRanking;
    private String driverCode;
    private String driverNationality;
    private String driverImgUrl;

    public DriverdataBO() {
    }




    public DriverdataBO(String driverName, String driverNumber, String driverConstructor, String driverPoints, int driverRanking, String driverCode, String driverNationality) {
        this.driverName = driverName;
        this.driverNumber = driverNumber;
        this.driverConstructor = driverConstructor;
        this.driverPoints = driverPoints;
        this.driverRanking = driverRanking;
        this.driverCode = driverCode;
        this.driverNationality = driverNationality;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverConstructor() {
        return driverConstructor;
    }

    public void setDriverConstructor(String driverConstructor) {
        this.driverConstructor = driverConstructor;
    }

    public String getDriverPoints() {
        return driverPoints;
    }

    public void setDriverPoints(String driverPoints) {
        this.driverPoints = driverPoints;
    }

    public int getDriverRanking() {
        return driverRanking;
    }

    public void setDriverRanking(int driverRanking) {
        this.driverRanking = driverRanking;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getDriverNationality() {
        return driverNationality;
    }

    public void setDriverNationality(String driverNationality) {
        this.driverNationality = driverNationality;
    }

    public String getDriverImgUrl() {
        return driverImgUrl;
    }

    public void setDriverImgUrl(String driverImgUrl) {
        this.driverImgUrl = driverImgUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
