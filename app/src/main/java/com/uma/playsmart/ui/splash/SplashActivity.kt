package com.uma.playsmart.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.uma.playsmart.R
import com.uma.playsmart.databinding.ActivitySplashBinding
import com.uma.playsmart.extensions.launchActivity
import com.uma.playsmart.ui.scorer.activity.ScorerActivity
import com.uma.playsmart.ui.teamDetails.TeamDetailsActivity
import com.uma.playsmart.ui.user.activity.UserActivity

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class SplashActivity :AppCompatActivity() , SplashView {


    lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.uma.playsmart.R.layout.activity_splash)

        splashBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        splashBinding.splashBinding = SplashVM(this)
    }

    override fun navigateToUser() {
        launchActivity<UserActivity> {  }
    }

    override fun navigateToScorer() {
        launchActivity<TeamDetailsActivity> {  }
    }

}
