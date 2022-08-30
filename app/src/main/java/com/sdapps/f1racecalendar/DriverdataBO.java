package com.sdapps.f1racecalendar;

public class DriverdataBO {

    private String driverName;
    private String driverNumber;
    private String driverConstructor;
    private String driverPoints;
    private int driverRanking;
    private String driverCode;
    private String driverNationality;
    private String DOB;
    private String driverId;
    private String position;
    private String totalPoints;
    private String wins;

    public DriverdataBO() {
    }

    public DriverdataBO(String driverName, String driverNumber, String driverConstructor, String driverPoints, int driverRanking, String driverCode, String driverNationality, String DOB, String driverId, String position, String totalPoints, String wins) {
        this.driverName = driverName;
        this.driverNumber = driverNumber;
        this.driverConstructor = driverConstructor;
        this.driverPoints = driverPoints;
        this.driverRanking = driverRanking;
        this.driverCode = driverCode;
        this.driverNationality = driverNationality;
        this.DOB = DOB;
        this.driverId = driverId;
        this.position = position;
        this.totalPoints = totalPoints;
        this.wins = wins;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }
}
