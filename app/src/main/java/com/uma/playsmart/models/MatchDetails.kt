package com.uma.playsmart.models

/**
 * Created by Umapathi on 05/03/19.
 * Copyright Indyzen Inc, @2019
 */
data class MatchDetails(var teamTotal: Int, var teamOvers: Double,var totalBalls : Int, var wickets: Int){
    constructor() : this(0,0.0,0,0)
}