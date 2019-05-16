package com.uma.playsmart.ui.scorer.fragment.batting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.uma.playsmart.R
import com.uma.playsmart.databinding.FragmentBattingBinding

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class BattingFragment : Fragment(),BattingView {

    lateinit var battingBinding: FragmentBattingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        battingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_batting,container,false)
        battingBinding.battingBinding = BattingVM(this)

        return battingBinding.root
    }
}
