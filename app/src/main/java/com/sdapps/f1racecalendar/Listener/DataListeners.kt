package com.sdapps.f1racecalendar.Listener

import com.sdapps.f1racecalendar.Model.DriverdataBO
import com.sdapps.f1racecalendar.Model.ConstructorBO
import com.sdapps.f1racecalendar.Model.CircuitBO

interface DataListeners {
    fun onSuccess(driverDataList: List<DriverdataBO?>?)
    fun onConSuccess(constructorBOList: List<ConstructorBO?>?)
    fun onCirSuccess(circuitBOS: List<CircuitBO?>?)
    fun onFail(msg: String?)
}