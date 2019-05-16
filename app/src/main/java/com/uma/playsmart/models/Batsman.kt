package com.uma.playsmart.models

/**
 * Created by Umapathi on 06/03/19.
 * Copyright Indyzen Inc, @2019
 */
data class Batsman(var name: String,
                   var status: String,
                   var runs: String,
                   var balls: String,
                   var fours: String,
                   var sixes: String,
                   var isStriking:String){
    constructor() : this("","","","","","","")
}