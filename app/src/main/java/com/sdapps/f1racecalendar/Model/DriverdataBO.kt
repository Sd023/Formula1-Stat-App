package com.sdapps.f1racecalendar.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_details")
 class DriverdataBO() {

    @ColumnInfo(name = "driver_name") var driverName: String?= null
    @ColumnInfo(name = "driver_number") var driverNumber: String?= null
    @ColumnInfo(name = "driver_code") var driverCode: String?= null
    @ColumnInfo(name = "driver_nationality") var driverNationality: String?= null
    @ColumnInfo(name = "driver_dob") var dob: String?= null
    @ColumnInfo(name = "driver_id") var driverId: String?= null
    @ColumnInfo(name = "driver_position") var position: String?= null
    @ColumnInfo(name = "total_points") var totalPoints: String?= null
    @ColumnInfo(name = "driver_wins") var wins: String? = null
    @ColumnInfo(name = "driver_cons_name") var constructorName: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0


}