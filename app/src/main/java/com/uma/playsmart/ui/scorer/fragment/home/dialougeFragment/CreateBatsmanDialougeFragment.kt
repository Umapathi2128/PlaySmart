package com.uma.playsmart.ui.scorer.fragment.home.dialougeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uma.playsmart.R
import com.uma.playsmart.datamanager.AppDataManager
import com.uma.playsmart.models.Batsman
import com.uma.playsmart.models.Bowler
import kotlinx.android.synthetic.main.add_players_dialogue.*

/**
 * Created by Umapathi on 05/04/19.
 * Copyright Indyzen Inc, @2019
 */
class CreateBatsmanDialougeFragment : DialogFragment(), CreateBatsmanView {

    lateinit var addPlayersDialogueBinding: com.uma.playsmart.databinding.AddPlayersDialogueBinding
    private lateinit var fDatabase: FirebaseDatabase

    private lateinit var batsman1Database: DatabaseReference
    private lateinit var bowlerDatabase: DatabaseReference
    private lateinit var mAppDataManager: AppDataManager
    private lateinit var matchId: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        addPlayersDialogueBinding = DataBindingUtil.inflate(inflater, R.layout.add_players_dialogue, container, false)
        addPlayersDialogueBinding.dialougeBinding = CreateBatsmanVM(this)

        return addPlayersDialogueBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fDatabase = FirebaseDatabase.getInstance()
        mAppDataManager = context?.let { AppDataManager(it) }!!
        matchId = mAppDataManager.getCurrentUserId()

    }

    override fun validateFirstBatsman(): Boolean {
        return etxtDialougeBatsman1.text.trim().toString() != "" && etxtDialougeBatsman1.text.isNotEmpty()
    }

    override fun validateSecondBatsman(): Boolean {
        return etxtDialougeBatsman2.text.trim().toString() != "" && etxtDialougeBatsman2.text.isNotEmpty()
    }

    override fun validateBowler(): Boolean {
        return etxtDialougeBowler.text.trim().toString() != "" && etxtDialougeBowler.text.isNotEmpty()
    }

    override fun showFirstbatsmanError() {
        etxtDialougeBatsman1.error = "Batsman must not be empty"
    }

    override fun showSecondBatsmanError() {
        etxtDialougeBatsman2.error = "Batsman must not be empty"
    }

    override fun showBowlerError() {
        etxtDialougeBowler.error = "Bowler must not be empty"
    }

    override fun resetClick() {
        etxtDialougeBatsman1.setText("")
        etxtDialougeBatsman2.setText("")
        etxtDialougeBowler.setText("")
    }

    override fun createButtonClick() {

        if (!validateFirstBatsman()) showFirstbatsmanError()
        else if (!validateSecondBatsman()) showSecondBatsmanError()
        else if (!validateBowler()) showBowlerError()
        else {
            val mAppDataManager = context?.let { AppDataManager(it) }

            mAppDataManager?.setBatsman1(etxtDialougeBatsman1.text.toString())
            mAppDataManager?.setBatsman2(etxtDialougeBatsman2.text.toString())
            mAppDataManager?.setBowler(etxtDialougeBowler.text.toString())

            createPlayers()

            dialog?.dismiss()
        }
    }

    /**
     * This is used for creating the openers and bowler...
     */
    private fun createPlayers() {

//        val createBatsmanDialougeFragment = CreateBatsmanDialougeFragment()
//        fragmentManager?.let { createBatsmanDialougeFragment.show(it, "dialogue") }

        createBatsman(
            mAppDataManager.getInnnings(), mAppDataManager.getBatsman1(), 1, 0, 0,
            0, 0,  1
        )
        createBatsman(
            mAppDataManager.getInnnings(), mAppDataManager.getBatsman2(), 1, 0, 0,
            0, 0, 0
        )

        createBowler(mAppDataManager.getInnnings(), mAppDataManager.getBowler())
    }

    private fun createBatsman(
        innings: String,
        batsmanName: String,
        status: Int,
        runs: Int,
        balls: Int,
        foursCount: Int,
        sixesCount: Int,
        isStriking: Int
    ) {

        batsman1Database = matchId.let {
            fDatabase.reference.child(it).child(innings).child("Batting")
                .child(batsmanName)
        }
        batsman1Database.setValue(
            Batsman(
                batsmanName,
                status.toString(),
                runs.toString(),
                balls.toString(),
                foursCount.toString(),
                sixesCount.toString(),
                isStriking.toString()
            )
        )
    }

    private fun createBowler(innings: String, bowlerName: String) {
        bowlerDatabase = matchId.let {
            fDatabase.reference.child(it).child(innings).child("Bowling")
                .child(bowlerName)
        }
        bowlerDatabase.setValue(Bowler(bowlerName, 0.0, 0, 0, 1,0))

    }

}