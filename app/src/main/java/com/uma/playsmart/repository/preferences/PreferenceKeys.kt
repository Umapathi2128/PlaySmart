package com.uma.playsmart.repository.preferences

/**
 * Created by Umapathi on 014/03/19.
 * Copyright Indyzen Inc, @2019
 */
interface PreferenceKeys {
    companion object {

        const val currentUserId = "UserId"
        const val preference_name = "preference_name"
        const val player1 = "player1_name"
        const val player2 = "player2_name"
        const val bowler = "bowler_name"
        const val teamA = "teamA"
        const val teamB = "teamB"
        const val totalOvers = "TotalOvers"
        const val isMatchInProcess = "IsMatchInProcess"
        const val battingTeam = "BattingTeam"
        const val innings = "Innings"
        const val innings1Score = "Innings1Score"
        const val innings2Score = "Innings2Score"
    }
}