package com.sdapps.f1racecalendar.Listener;

import com.sdapps.f1racecalendar.Model.CircuitBO;
import com.sdapps.f1racecalendar.Model.ConstructorBO;
import com.sdapps.f1racecalendar.Model.DriverdataBO;

import java.util.List;

public interface JSONCall {
    void onSuccess(List<DriverdataBO> driverDataList);
    void onConSuccess(List<ConstructorBO> constructorBOList);
    void onCirSuccess(List<CircuitBO> circuitBOS);
    void onFail(String msg);
}
