package com.uma.playsmart.models

/**
 * Created by Umapathi on 06/03/19.
 * Copyright Indyzen Inc, @2019
 */
data class Bowler(
    var name: String,
    var overs: Double,
    var runs: Int,
    var wickets: Int,
    var status: Int,
    var balls : Int
) {
    constructor() : this("",0.0,0,0,0,0)
}