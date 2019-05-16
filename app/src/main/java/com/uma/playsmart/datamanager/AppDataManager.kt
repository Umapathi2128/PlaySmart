package com.uma.playsmart.datamanager

import android.content.Context
import com.uma.playsmart.repository.preferences.AppPreferenceHelper
import com.uma.playsmart.repository.preferences.PreferenceKeys

/**
 * Created by Umapathi on 14/03/19.
 * Copyright Indyzen Inc, @2019
 */
class AppDataManager(
    private var ctx: Context,
    private val mAppPreferenceHelper: AppPreferenceHelper = AppPreferenceHelper(ctx)
//    var firebaseReference: FirebaseReference = FirebaseReference(ctx)
) : DataManagerHelper {

    override fun setInnings1Score(score: Int) {
        mAppPreferenceHelper.putInt(PreferenceKeys.innings1Score, score)
    }

    override fun getInnings1Score(): Int {
        return mAppPreferenceHelper.getInt(PreferenceKeys.innings1Score)!!
    }

    override fun setInnings2Score(score: Int) {
        mAppPreferenceHelper.putInt(PreferenceKeys.innings2Score, score)
    }

    override fun getInnings2Score() =
        mAppPreferenceHelper.getInt(PreferenceKeys.innings2Score)!!


    override fun setInnings(innings: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.innings, innings)
    }

    override fun getInnnings()=
         mAppPreferenceHelper.getString(PreferenceKeys.innings)!!


    override fun setBattingTeam(team: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.battingTeam, team)
    }

    override fun getBattingTeam()=
         mAppPreferenceHelper.getString(PreferenceKeys.battingTeam)!!


    override fun setmatchProcess(isMatchInProcess: Boolean) {
        mAppPreferenceHelper.putBoolean(PreferenceKeys.isMatchInProcess, isMatchInProcess)
    }

    override fun getMatchProcess(): Boolean {
        return mAppPreferenceHelper.getBoolean(PreferenceKeys.isMatchInProcess)!!
    }

    override fun setTotalOvers(overs: Int) {
        mAppPreferenceHelper.putInt(PreferenceKeys.totalOvers, overs)
    }

    override fun getTotalOvers(): Int {
        return mAppPreferenceHelper.getInt(PreferenceKeys.totalOvers)!!
    }


    override fun setTeamA(teamA: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.bowler, teamA)
    }

    override fun getTeamA(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.teamA)!!
    }

    override fun setTeamB(teamB: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.bowler, teamB)
    }

    override fun getTeamB(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.teamB)!!
    }

    override fun setBowler(bowler: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.bowler, bowler)
    }

    override fun getBowler(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.bowler)!!
    }

    override fun setBatsman1(batsman1: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.player1, batsman1)
    }

    override fun getBatsman1(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.player1)!!
    }

    override fun setBatsman2(batsman2: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.player2, batsman2)
    }

    override fun getBatsman2(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.player2)!!
    }
    override fun getCurrentUserId(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.currentUserId)!!
    }

    override fun setCurrentUserId(value: String) {
        mAppPreferenceHelper.putString(PreferenceKeys.currentUserId, value)
    }
}