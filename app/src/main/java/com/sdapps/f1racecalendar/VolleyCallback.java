package com.sdapps.f1racecalendar;

import java.util.HashMap;

public interface VolleyCallback {
    void responseOK(HashMap<String, String>driverNameList);
    void responseError(String error);
}
