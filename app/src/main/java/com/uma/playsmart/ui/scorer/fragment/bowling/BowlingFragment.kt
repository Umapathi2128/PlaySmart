package com.uma.playsmart.ui.scorer.fragment.bowling


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.uma.playsmart.R
import com.uma.playsmart.databinding.FragmentBowlingBinding

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class BowlingFragment : Fragment(),BowlingView {

    lateinit var bowlingBinding: FragmentBowlingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bowlingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bowling,container,false)
        bowlingBinding.bowlingBinding = BowlingVM(this)

        return bowlingBinding.root
    }
}
