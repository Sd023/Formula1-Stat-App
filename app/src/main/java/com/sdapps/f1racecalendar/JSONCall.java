package com.sdapps.f1racecalendar;

public interface JSONCall {
    void onSuccess(String driverName);
    void onFail(String msg);
}
