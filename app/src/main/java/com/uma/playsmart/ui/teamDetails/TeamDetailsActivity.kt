package com.uma.playsmart.ui.teamDetails

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uma.playsmart.R
import com.uma.playsmart.databinding.ActivityTeamDetailsBinding
import com.uma.playsmart.datamanager.AppDataManager
import com.uma.playsmart.extensions.launchActivity
import com.uma.playsmart.models.MatchDataModel
import com.uma.playsmart.models.MatchDetails
import com.uma.playsmart.ui.scorer.activity.ScorerActivity
import kotlinx.android.synthetic.main.activity_team_details.*

class TeamDetailsActivity : AppCompatActivity(), TeamDetailsView {

    private lateinit var teamDetailsBinding: ActivityTeamDetailsBinding
    private lateinit var mAppDataManager: AppDataManager
    private lateinit var fDatabase: FirebaseDatabase
    private lateinit var dReference: DatabaseReference
    private var tossTeam = ""
    private var tossDecision = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAppDataManager = AppDataManager(this)

        teamDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_team_details)
        teamDetailsBinding.teamDetailsBinding = TeamDetailsVM(this)

        setTextToRadioButtons()
    }

    private fun setTextToRadioButtons() {
        etxtMatchPlace.addTextChangedListener {
            if (etxtTeamB.text.toString().isNotEmpty() && etxtTeamA.text.toString().isNotEmpty()) {
//                mAppDataManager.se
                teamA.text = etxtTeamA.text.toString()
                teamB.text = etxtTeamB.text.toString()
            }
        }
    }

    /**
     * This method for write data into firebase...
     */
    private fun saveDataToFirebase() {

        FirebaseApp.initializeApp(this)
        fDatabase = FirebaseDatabase.getInstance()

        val radioGroup = radioGroup.checkedRadioButtonId
        val radioGroupDecision = radioGroupdecision.checkedRadioButtonId

        val toss = findViewById<RadioButton>(radioGroup)
        val decision = findViewById<RadioButton>(radioGroupDecision)

        tossTeam = toss.text.toString()
        tossDecision = decision.text.toString()

        val matchDataModel = MatchDataModel(
            etxtUmpire.text.toString(), etxtMatchPlace.text.toString(),
            etxtTeamA.text.toString(), etxtTeamB.text.toString(), etxtOvers.text.toString(), tossTeam
            , tossDecision
        )

        dReference = fDatabase.getReference(etxtMatchID.text.toString())
        dReference.setValue(matchDataModel)

        dReference =
            fDatabase.getReference(etxtMatchID.text.toString()).child("Innings1").child("Details")
        dReference.setValue(MatchDetails(0, 0.0, 0, 0))
        dReference =
            fDatabase.getReference(etxtMatchID.text.toString()).child("Innings2").child("Details")
        dReference.setValue(MatchDetails(0, 0.0, 0, 0))

        when {
            tossTeam == etxtTeamA.text.toString() && tossDecision == "bat" -> {
                mAppDataManager.setBattingTeam(etxtTeamA.text.toString())
            }
            tossTeam == etxtTeamA.text.toString() && tossDecision == "bowl" -> {
                mAppDataManager.setBattingTeam(etxtTeamB.text.toString())
            }
            tossTeam == etxtTeamB.text.toString() && tossDecision == "bowl" -> {
                mAppDataManager.setBattingTeam(etxtTeamA.text.toString())
            }
            tossTeam == etxtTeamB.text.toString() && tossDecision == "bat" -> {
                mAppDataManager.setBattingTeam(etxtTeamB.text.toString())
            }
        }
    }

    override fun validateMatchId() =
        etxtMatchID.text.trim().toString() != "" && etxtMatchID.text.trim().toString().isNotEmpty()

    override fun showMatchIdError() {
        etxtMatchID.error = "match id must not be null"
    }

    override fun validateUmpire() =
        etxtUmpire.text.trim().toString() != "" && etxtUmpire.text.trim().toString().isNotEmpty()

    override fun showUmpireError() {
        etxtUmpire.error = "Umpire name must not be null"
    }

    override fun validateTeamA() =
        etxtTeamA.text.trim().toString() != "" && etxtTeamA.text.trim().toString().isNotEmpty()

    override fun showTeamAError() {
        etxtTeamA.error = "TeamA  must not be null"
    }

    override fun validateTeamB() =
        etxtTeamB.text.trim().toString() != "" && etxtTeamB.text.trim().toString().isNotEmpty()

    override fun showTeamBError() {
        etxtTeamB.error = "TeamB  must not be null"
    }

    override fun validateMatchPlace() =
        etxtMatchPlace.text.trim().toString() != "" && etxtMatchPlace.text.trim().toString().isNotEmpty()

    override fun showMatchPlaceError() {
        etxtMatchPlace.error = "MatchPlace  must not be null"
    }

    override fun validateTotalOvers() =
        etxtOvers.text.trim().toString() != "" && etxtOvers.text.trim().toString().isNotEmpty()

    override fun showOversError() {
        etxtOvers.error = "Total overs must not be null"
    }

    override fun saveDataIntoFirebase() {
        when {
            radioGroup.checkedRadioButtonId == -1 -> {
                val msg = "Choose the toss winning team ..."
                txtError.text = msg
            }
            radioGroupdecision.checkedRadioButtonId == -1 -> {
                val msg = "Choose the toss Decision..."
                txtError.text = msg
            }
            else -> {

                mAppDataManager.apply {
                    setInnings("Innings1")
                    setTeamA(etxtTeamA.text.toString())
                    setTeamB(etxtTeamB.text.toString())
                    setCurrentUserId(etxtMatchID.text.toString())
                    setTotalOvers((etxtOvers.text.toString()).toInt())
                }
//                mAppDataManager.setInnings("Innings1")
//                mAppDataManager.setTeamA(etxtTeamA.text.toString())
//                mAppDataManager.setTeamB(etxtTeamB.text.toString())
//                mAppDataManager.setCurrentUserId(etxtMatchID.text.toString())
//                mAppDataManager.setTotalOvers((etxtOvers.text.toString()).toInt())
                saveDataToFirebase()
                launchActivity<ScorerActivity> { }
            }
        }
    }
}
