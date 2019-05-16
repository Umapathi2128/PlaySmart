package com.uma.playsmart.ui.teamDetails

/**
 * Created by Umapathi on 04/04/19.
 * Copyright Indyzen Inc, @2019
 */
interface TeamDetailsView {

    fun validateMatchId() : Boolean

    fun showMatchIdError()

    fun validateUmpire():Boolean

    fun showUmpireError()

    fun validateTeamA():Boolean

    fun showTeamAError()

    fun validateTeamB():Boolean

    fun showTeamBError()

    fun validateMatchPlace():Boolean

    fun showMatchPlaceError()

    fun validateTotalOvers():Boolean

    fun showOversError()

    fun saveDataIntoFirebase()
}