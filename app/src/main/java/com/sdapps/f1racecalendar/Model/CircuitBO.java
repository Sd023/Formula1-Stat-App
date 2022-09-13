package com.sdapps.f1racecalendar.Model;

public class CircuitBO {
    private String date;
    private String time;
    private String raceName;
    private String circuitId;
    private int season;
    private int id;

    public CircuitBO() {
    }

    public CircuitBO(String date, String time, String raceName, String circuitId, int season, int id) {
        this.date = date;
        this.time = time;
        this.raceName = raceName;
        this.circuitId = circuitId;
        this.season = season;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
