package com.uma.playsmart.ui.user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.uma.playsmart.R
import com.uma.playsmart.databinding.ActivityUserBinding

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class UserActivity : AppCompatActivity(), UserView {

    private lateinit var userBinding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        userBinding.userBinding = UserVM(this)
    }
}
