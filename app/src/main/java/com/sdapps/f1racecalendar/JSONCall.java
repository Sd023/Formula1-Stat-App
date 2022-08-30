package com.sdapps.f1racecalendar;

import java.util.List;

public interface JSONCall {
    void onSuccess(List<DriverdataBO> driverDataList);
    void onConSuccess(List<ConstructorBO> constructorBOList);
    void onFail(String msg);
}
