package com.uma.playsmart.ui.user.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.uma.playsmart.R
import com.uma.playsmart.databinding.FragmentUserHomeBinding

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class UserHomeFragment : Fragment(),UserHomeView {

    lateinit var userHomeBinding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_home,container,false)
        userHomeBinding.userHomeBinding = UserHomeVM(this)

        return userHomeBinding.root
    }
}
