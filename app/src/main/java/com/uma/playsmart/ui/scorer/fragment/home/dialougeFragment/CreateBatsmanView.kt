package com.uma.playsmart.ui.scorer.fragment.home.dialougeFragment

/**
 * Created by Umapathi on 05/04/19.
 * Copyright Indyzen Inc, @2019
 */
interface CreateBatsmanView {

    fun validateFirstBatsman() : Boolean

    fun validateSecondBatsman() : Boolean

    fun validateBowler() :Boolean

    fun showFirstbatsmanError()

    fun showSecondBatsmanError()

    fun showBowlerError()

    fun resetClick()

    fun createButtonClick()
}