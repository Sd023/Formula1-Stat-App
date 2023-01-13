package com.sdapps.f1racecalendar.Model

class CircuitBO {
    var date: String? = null
    var time: String? = null
    var raceName: String? = null
    var circuitId: String? = null
    var season = 0
    var id = 0

    constructor() {}
    constructor(
        date: String?,
        time: String?,
        raceName: String?,
        circuitId: String?,
        season: Int,
        id: Int
    ) {
        this.date = date
        this.time = time
        this.raceName = raceName
        this.circuitId = circuitId
        this.season = season
        this.id = id
    }
}