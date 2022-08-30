package com.sdapps.f1racecalendar;

public class ConstructorBO {
    private String position;
    private String constructorName;
    private String nationality;
    private String points;
    private String wins;
    private String conId;

    public ConstructorBO() {
    }

    public ConstructorBO(String position, String constructorName, String nationality, String points, String wins, String conId) {
        this.position = position;
        this.constructorName = constructorName;
        this.nationality = nationality;
        this.points = points;
        this.wins = wins;
        this.conId = conId;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public void setConstructorName(String constructorName) {
        this.constructorName = constructorName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }
}
