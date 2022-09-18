package com.sdapps.f1racecalendar.Model

class ConstructorBO {
    var position: String? = null
    var constructorName: String? = null
    var nationality: String? = null
    var points: String? = null
    var wins: String? = null
    var conId: String? = null

    constructor() {}
    constructor(
        position: String?,
        constructorName: String?,
        nationality: String?,
        points: String?,
        wins: String?,
        conId: String?
    ) {
        this.position = position
        this.constructorName = constructorName
        this.nationality = nationality
        this.points = points
        this.wins = wins
        this.conId = conId
    }
}