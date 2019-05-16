package com.uma.playsmart.ui.scorer.fragment.home.dialougeFragment

/**
 * Created by Umapathi on 05/04/19.
 * Copyright Indyzen Inc, @2019
 */
class CreateBatsmanVM(var createBatsmanView: CreateBatsmanView) {


    fun resetButtonClick() {
        createBatsmanView.resetClick()
    }

    fun createBatsman() {
        createBatsmanView.createButtonClick()
    }

}