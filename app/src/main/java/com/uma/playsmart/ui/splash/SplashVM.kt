package com.uma.playsmart.ui.splash

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class SplashVM(var splashView: SplashView) {

    /**
     * This method is used to navigate to the user activity....
     */
    fun navigateToUser()
    {
        splashView.navigateToUser()
    }
    /**
     * This method is used to navigate to the scorer activity....
     */
    fun navigateToScorer(){
        splashView.navigateToScorer()
    }
}