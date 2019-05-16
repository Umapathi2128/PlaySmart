package com.uma.playsmart.ui.teamDetails

/**
 * Created by Umapathi on 04/04/19.
 * Copyright Indyzen Inc, @2019
 */
class TeamDetailsVM(var teamDetailsView: TeamDetailsView) {

    fun saveDataIntoFirebase(){
        if (!teamDetailsView.validateMatchId()) teamDetailsView.showMatchIdError()
        else if (!teamDetailsView.validateUmpire()) teamDetailsView.showUmpireError()
        else if (!teamDetailsView.validateTeamA()) teamDetailsView.showTeamAError()
        else if (!teamDetailsView.validateTeamB()) teamDetailsView.showTeamBError()
        else if (!teamDetailsView.validateMatchPlace()) teamDetailsView.showMatchPlaceError()
        else if (!teamDetailsView.validateTotalOvers()) teamDetailsView.showOversError()
        else teamDetailsView.saveDataIntoFirebase()
    }
}