package com.uma.playsmart.ui.scorer.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.playsmart.R
import com.uma.playsmart.databinding.ActivityScorerBinding
import com.uma.playsmart.extensions.replaceFragment
import com.uma.playsmart.ui.scorer.fragment.batting.BattingFragment
import com.uma.playsmart.ui.scorer.fragment.bowling.BowlingFragment
import com.uma.playsmart.ui.scorer.fragment.home.HomeFragment
import kotlinx.android.synthetic.main.activity_scorer.*

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class ScorerActivity : AppCompatActivity(), ScorerView, BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var scorerBinding: ActivityScorerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scorerBinding = DataBindingUtil.setContentView(this, R.layout.activity_scorer)
        scorerBinding.scorerBinding = ScorerVM(this)

        replaceFragment(HomeFragment(), R.id.framelayout, "")
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHome -> {
                replaceFragment(HomeFragment(), R.id.framelayout, "Home")
            }
            R.id.menuBatting -> {
                replaceFragment(BattingFragment(), R.id.framelayout, "Batting")
            }
            R.id.menuBowling -> {
                replaceFragment(BowlingFragment(), R.id.framelayout, "Bowling")
            }
            else -> {
            }
        }
        return true
    }
}
