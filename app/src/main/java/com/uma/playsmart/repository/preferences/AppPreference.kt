package com.uma.playsmart.repository.preferences


/**
 * Created by Umapathi on 14/03/19.
 * Copyright Indyzen Inc, @2019
 */

interface AppPreference {

    fun getCurrentUserId(): String

    fun setCurrentUserId(value: String)

    fun setBatsman1(batsman1: String)

    fun getBatsman1(): String

    fun setBatsman2(batsman2: String)

    fun getBatsman2(): String

    fun setBowler(bowler: String)

    fun getBowler(): String

    fun setTeamA(teamA:String)

    fun getTeamA():String

    fun setTeamB(teamB:String)

    fun getTeamB():String

    fun setTotalOvers(overs:Int)

    fun getTotalOvers():Int

    fun setmatchProcess(isMatchInProcess:Boolean)

    fun getMatchProcess():Boolean

    fun setBattingTeam(team : String)

    fun getBattingTeam() : String

    fun setInnings(innings : String)

    fun getInnnings() : String

    fun setInnings1Score(score : Int)

    fun getInnings1Score() :Int

    fun setInnings2Score(score : Int)

    fun getInnings2Score() :Int
}