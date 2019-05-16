package com.uma.playsmart.ui.scorer.fragment.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.uma.playsmart.R
import com.uma.playsmart.databinding.FragmentHomeBinding
import com.uma.playsmart.datamanager.AppDataManager
import com.uma.playsmart.models.Batsman
import com.uma.playsmart.models.Bowler
import com.uma.playsmart.models.MatchDetails
import com.uma.playsmart.repository.singleton.FirebaseReference
import com.uma.playsmart.ui.scorer.fragment.home.dialougeFragment.CreateBatsmanDialougeFragment
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.score_view_layout.*
import java.text.DecimalFormat

/**
 * Created by Umapathi on 02/04/19.
 * Copyright Indyzen Inc, @2019
 */
class HomeFragment : Fragment(), HomeView, View.OnClickListener {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var batsman1Database: DatabaseReference
    private lateinit var mAppDataManager: AppDataManager

    private lateinit var firebaseReference: FirebaseReference

    private var totalScore: Int = 0
    private var teamWicketsCount = 0
    private var teamOversCount = 0

    private var bt1FoursCount: Int = 0
    private var bt1SixesCount: Int = 0
    private var isPlayersCreated = true


    private lateinit var batsmanList: ArrayList<Batsman>
    private lateinit var bowlerList: ArrayList<Bowler>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeBinding.homeBinding = HomeVM(this)

        return homeBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        scoreList = ArrayList()
        batsmanList = ArrayList()
        bowlerList = ArrayList()
//        battingAdapter = BattingAdapter(scoreList)

        mAppDataManager = context?.let { AppDataManager(it) }!!
        firebaseReference = FirebaseReference()

        retrieveBatsman()
        retrieveBowler()
        retrieveScore()

        getAllBatsmanNames()
        getAllBowler()
    }

    override fun onResume() {
        super.onResume()
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
        btnThree.setOnClickListener(this)
        btnFour.setOnClickListener(this)
        btnFive.setOnClickListener(this)
        btnSix.setOnClickListener(this)
        btnZero.setOnClickListener(this)
        btnWide.setOnClickListener(this)
        btnNb.setOnClickListener(this)
        btnLb.setOnClickListener(this)
        btnByes.setOnClickListener(this)
        btnOut.setOnClickListener(this)
        btnSecondInnings.setOnClickListener(this)

        txt1TeamName.text = mAppDataManager.getBattingTeam()
//        txt1TeamScore.text = totalScore.toString()
//        txt1TeamWicket.text = teamWicketsCount.toString()
//        txt1TeamOvers.text = "($teamOversCount)"

    }

    override fun onClick(v: View?) {
        when (v) {

            btnSecondInnings -> {
                notification_layout.visibility = View.GONE
                firebaseReference.isInningsComplete = true
                createPlayers()
            }
            btnOne -> {
                setBatsmanData(1)
//                retriveDataFromFirebase()
            }
            btnTwo -> {
                setBatsmanData(2)
            }
            btnThree -> {
                setBatsmanData(3)
            }
            btnFour -> {
                bt1FoursCount += 1
                setBatsmanData(4)
            }
            btnFive -> {
                setBatsmanData(5)
            }
            btnSix -> {
                bt1SixesCount += 1
                setBatsmanData(6)
            }
            btnZero -> {
                setBatsmanData(0)
            }
            btnWide -> {
                setBatsmanData(7)
            }
            btnNb -> {
                setBatsmanData(8)
            }
            btnLb -> {
                setBatsmanData(9)
            }
            btnByes -> {
                setBatsmanData(10)
            }
            btnOut -> {
                setBatsmanData(11)
                Toast.makeText(context, "OUT", Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }
    }

    /**
     * This method for save batsman data...
     * In this method isStriking 1 means (Striker)current playing batsman and 0 means non_striker..
     */
    private fun setBatsmanData(data: Int) {

        var batsmanName = ""
        var batsman2Name = ""
        var bowlerName = ""
        var runs = 0
        var balls = 0
        var fours = 0
        var sixes = 0
        var isStriking = 0
        var batsmanStatus = "0"

        var runsGiven = 0
        var wickets = 0
        var ballsBowled = 0
        var oversBowled: Double
        var bowlerStatus = 1

        var totalBalls = teamOversCount
        var teamWickets = teamWicketsCount



        getAllBatsmanNames()

        for (i in 0 until batsmanList.size) {
            if (batsmanList[i].status == (1).toString() && batsmanList[i].isStriking == "1") {
                batsmanName = batsmanList[i].name
                runs = batsmanList[i].runs.toInt()
                balls = batsmanList[i].balls.toInt()
                fours = batsmanList[i].fours.toInt()
                sixes = batsmanList[i].sixes.toInt()
                isStriking = batsmanList[i].isStriking.toInt()
                batsmanStatus = batsmanList[i].status
            } else if (batsmanList[i].status == (1).toString() && batsmanList[i].isStriking == "0") {
                batsman2Name = batsmanList[i].name
            }
        }

        getAllBowler()

        for (i in 0 until bowlerList.size) {
            if (bowlerList[i].status == 1) {
                bowlerName = bowlerList[i].name
                runsGiven = bowlerList[i].runs
                wickets = bowlerList[i].wickets
                bowlerStatus = bowlerList[i].status
                ballsBowled = bowlerList[i].balls
            }
        }

        try {
            when (data) {
                0 -> {
                    // batting...
                    totalScore += 0
                    balls += 1
                    runs += 0
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                }
                1 -> {
                    // batting...
                    isStriking = 0
                    totalScore += 1
                    balls += 1
                    runs += 1
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 1
                }
                2 -> {
                    // batting...
                    isStriking = 1
                    totalScore += 2
                    balls += 1
                    runs += 2
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 2
                }
                3 -> {
                    // batting...
                    isStriking = 0
                    totalScore += 3
                    balls += 1
                    runs += 3
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 3
                }
                4 -> {
                    // batting...
                    isStriking = 1
                    fours += 1
                    totalScore += 4
                    balls += 1
                    runs += 4
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 4
                }
                5 -> {
                    // batting...
                    isStriking = 0
                    totalScore += 5
                    balls += 1
                    runs += 5
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 5
                }
                6 -> {
                    // batting...
                    isStriking = 1
                    sixes += 1
                    totalScore += 6
                    balls += 1
                    runs += 6
                    totalBalls += 1
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 6
                }
                7 -> { // HERE 7 is considered as Wide ball...
                    // batting...
                    isStriking = 1
                    totalScore += 1
                    balls += 0
                    runs += 0
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 1
                }
                8 -> { // HERE 8 is considered as No ball...
                    // batting...
                    isStriking = 1
                    totalScore += 1
                    balls += 1
                    runs += 0
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 1
                }
                9 -> {// HERE 9 is considered as Leg byes...
                    // batting...
                    isStriking = 0
                    totalScore += 1
                    balls += 1
                    runs += 0
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 1

                }
                10 -> {// HERE 10 is considered as Byes...
                    // batting...
                    isStriking = 0
                    totalScore += 1
                    balls += 1
                    runs += 0
                    // Bowling...
                    ballsBowled += 1
                    runsGiven += 1
                }
                11 -> {// HERE 11 is considered as Out...
                    // batting...
                    totalScore += 0
                    batsmanStatus = "0"
                    balls += 1
                    runs += 0
                    teamWickets += 1
                    // Bowling...
                    wickets += 1
                }
                else -> {
                }
            }
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }
        val batsman2Database1: DatabaseReference =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Batting").child(batsmanName)


        val overs = convertBallsToOvers(balls)

        if (totalBalls % 6 == 0 && isStriking == 1) {
            isStriking = 0
//            bowlerStatus = 0
        }

        if (isStriking == 1 && batsmanName != "") {
            batsman2Database1.setValue(
                Batsman(
                    batsmanName,
                    batsmanStatus,
                    runs.toString(),
                    balls.toString(),
                    fours.toString(),
                    sixes.toString(),
                    isStriking.toString()
                )
            )

        } else if (isStriking == 0 && batsmanName != "") {
            batsman2Database1.setValue(
                Batsman(
                    batsmanName,
                    batsmanStatus,
                    runs.toString(),
                    balls.toString(),
                    fours.toString(),
                    sixes.toString(),
                    isStriking.toString()
                )
            )

            val batsman2Database = firebaseReference.referenceWithInnings(
                mAppDataManager.getInnnings(),
                mAppDataManager.getCurrentUserId()
            ).child("Batting").child(batsman2Name)
                .child("striking")

            batsman2Database.setValue("1")
        }
        setTeamTotal(totalScore, totalBalls, teamWickets, overs)

        if (bowlerStatus == 1 && bowlerName != "") {
            val ref = firebaseReference.referenceWithInnings(
                mAppDataManager.getInnnings(),
                mAppDataManager.getCurrentUserId()
            ).child("Bowling").child(bowlerName)

            oversBowled = convertBallsToOvers(ballsBowled)
            ref.setValue(Bowler(bowlerName, oversBowled, runsGiven, wickets, bowlerStatus, ballsBowled))
        }
    }

    private fun setTeamTotal(total: Int, balls: Int, wickets: Int, overs: Double) {
        val query: DatabaseReference =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Details")

        query.setValue(MatchDetails(total, overs, balls, wickets))
    }


    private fun createPlayers() {

        val createBatsmanDialougeFragment = CreateBatsmanDialougeFragment()
        createBatsmanDialougeFragment.isCancelable = false
        fragmentManager?.let { createBatsmanDialougeFragment.show(it, "dialogue") }
    }

    /**
     *  This method is used for getting the team total score and setting it into textview....
     */
    private fun getAllBatsmanNames() {

//        batsmanList = ArrayList()

        val batsmanList1 =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Batting")

        batsmanList1.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("Uma list abc", "" + p0)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.value == null && isPlayersCreated) {
                    mAppDataManager.setInnings("Innings1")
                    createPlayers()
//                    isPlayersCreated = false
                    return

                }
                val map: Map<*, *> = dataSnapshot.value as Map<*, *>

                val list = ArrayList(map.keys)

                for (i in 0 until list.size) {
                    batsman1Database = firebaseReference.referenceWithInnings(
                        mAppDataManager.getInnnings(),
                        mAppDataManager.getCurrentUserId()
                    ).child("Batting")
                        .child(list[i].toString())


                    batsman1Database.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            Log.e("Uma list abc", "" + p0)
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            batsmanList.add(p0.getValue(Batsman::class.java)!!)
//                            else if (type == "Bowling") bowlerList.add(p0.getValue(Bowler::class.java)!!)
                        }
                    })
                }
            }
        })
    }

    /**
     *  This method is used for getting the team total score and setting it into textview....
     */
    private fun getAllBowler() {

        val batsmanList1 =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Bowling")

        batsmanList1.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("Uma list abc", "" + p0)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.value == null) {
//                    mAppDataManager.setInnings("Innings1")
//                    createPlayers()
//                    isPlayersCreated = false
                    return
                }
                val map: Map<*, *> = dataSnapshot.value as Map<*, *>

                val list = ArrayList(map.keys)

                for (i in 0 until list.size) {
                    batsman1Database = firebaseReference.referenceWithInnings(
                        mAppDataManager.getInnnings(),
                        mAppDataManager.getCurrentUserId()
                    ).child("Bowling")
                        .child(list[i].toString())


                    try {
                        batsman1Database.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                Log.e("Uma list abc", "" + p0)
                            }

                            override fun onDataChange(p0: DataSnapshot) {

                                if (p0 != null )
                                        bowlerList.add(p0.getValue(Bowler::class.java)!!)
                            }
                        })
                    }catch (e : Exception){
                        Log.e("OnDataChangeException",e.message)
                    }
                }
            }
        })
    }

    /**
     *  This method is used for getting the Bowler details and setting it into textViews....
     */
    @SuppressLint("CheckResult")
    fun retrieveBowler() {

        val databaseReference =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Bowling")
        RxFirebaseDatabase.observeValueEvent(databaseReference, DataSnapshotMapper.listOf(Bowler::class.java))
            .subscribe {
                for (i in 0 until it.size) {
                    if (it[i].status == 1) {
                        try {
                            val dec = DecimalFormat("#0.00")

                            txtBowlerName.text = it[i].name
                            val economy = ((it[i].runs.toDouble() / it[i].balls) * 6)
                            txtBowlerEconomy.text = dec.format(economy).toString()
                            txtBowledOvers.text = it[i].overs.toString()
                            txtRunsGiven.text = it[i].runs.toString()
                            txtwicketsTaken.text = it[i].wickets.toString()
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
    }

    /**
     *  This method is used for getting the team total score and setting it into textview....
     */
    @SuppressLint("CheckResult", "SetTextI18n")
    private fun retrieveScore() {

        val databaseReference =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
        RxFirebaseDatabase.observeValueEvent(databaseReference.child("Details"), MatchDetails::class.java)
            .subscribe {

                try {
                    val totalBalls = mAppDataManager.getTotalOvers() * 6

                    if (mAppDataManager.getInnnings() == "Innings2") {

                        val team: String
                        if (mAppDataManager.getBattingTeam() == mAppDataManager.getTeamA()) {
                            txt1TeamName.text =
                                mAppDataManager.getTeamB()
                            team = mAppDataManager.getTeamB()
                        } else {
                            txt1TeamName.text = mAppDataManager.getTeamA()
                            team = mAppDataManager.getTeamA()
                        }

                        val totalRuns = mAppDataManager.getInnings1Score() - it.teamTotal

                        txtInningsDetails.text = (team + " needs " + totalRuns +
                                " of " + (totalBalls - it.totalBalls))
                    }

                    // this line for getting match is currently playing or not...
                    if (totalBalls <= it.totalBalls) {
                        if (mAppDataManager.getInnnings() == "Innings1") {
                            mAppDataManager.setInnings1Score(it.teamTotal)
                            mAppDataManager.setInnings("Innings2")
//                            firebaseReference.isInningsComplete = true
//                            createPlayers()
//                            batsmanList = ArrayList()
                            notification_layout.visibility = View.VISIBLE

//                            getAllBatsmanNames()
//                            getAllBowler()
//                            retrieveScore()
//                            retrieveBatsman()
//                            retrieveBowler()
                        } else if (mAppDataManager.getInnnings() == "Innings2") {
                            mAppDataManager.setInnings2Score(it.teamTotal)
                            mAppDataManager.setmatchProcess(false)
                            when {
                                mAppDataManager.getInnings1Score() == it.teamTotal -> {
                                    Toast.makeText(context, "Tie", Toast.LENGTH_SHORT).show()
                                }
                                mAppDataManager.getInnings1Score() < it.teamTotal -> {
//                                    notification_layout.visibility = View.VISIBLE
//                                    txtNotification.text = mAppDataManager.getBattingTeam()
                                    Toast.makeText(context, "The wining team is ", Toast.LENGTH_SHORT).show()
                                }
                                mAppDataManager.getInnings1Score() > it.teamTotal -> {
                                    Toast.makeText(
                                        context,
                                        "The wining team is" + mAppDataManager.getBattingTeam(),
                                        Toast.LENGTH_SHORT
                                    ).show()
//                                    notification_layout.visibility = View.VISIBLE
//                                    txtNotification.text = "uma"
                                }
                            }
                        }

                    } else mAppDataManager.setmatchProcess(true)

                    totalScore = it.teamTotal
                    teamOversCount = it.totalBalls
                    teamWicketsCount = it.wickets
                    txt1TeamScore.text = it.teamTotal.toString()
                    txt1TeamOvers.text = "(" + convertBallsToOvers(it.totalBalls).toString() + ")"
                    txt1TeamWicket.text = it.wickets.toString()

                    val dec = DecimalFormat("#0.00")

                    val crr: Double = ((totalScore.toDouble() / teamOversCount.toDouble()) * 6)
                    txtCurrentRunRate.text = "CRR : " + dec.format(crr).toString()
                } catch (e: Exception) {
                    Log.e("Exception ", e.toString())
                }
            }
    }

    /**
     *  This method is used for getting the Batsman details and setting it into textview....
     */
    @SuppressLint("CheckResult")
    fun retrieveBatsman() {

        val databaseReference =
            firebaseReference.referenceWithInnings(mAppDataManager.getInnnings(), mAppDataManager.getCurrentUserId())
                .child("Batting")
        RxFirebaseDatabase.observeValueEvent(
            databaseReference,
            DataSnapshotMapper.listOf(Batsman::class.java)
        ).subscribe {
            //txt3.text = it[0].name
            try {
                for (i in 0 until it.size) {
                    if (it[i].status == "1") {
                        if (it[i].isStriking == "1") {
                            txtBatsmanName1.text = it[i].name
                            txtBatsmanRuns1.text = it[i].runs
                            txtBatsmanBalls1.text = it[i].balls

                            val dec = DecimalFormat("#0.00")

                            val sr: Double = (it[i].runs.toDouble() / it[i].balls.toDouble()) * 100
                            txtBatsmansFours1.text = it[i].fours
                            txtBatsmanSixes1.text = it[i].sixes
                            txtBatsmanStrikeRate1.text = dec.format(sr).toString()
                            bt1FoursCount = it[i].fours.toInt()

                        } else {
                            txtBatsmanName2.text = it[i].name
                            txtBatsmanRuns2.text = it[i].runs
                            txtBatsmanBalls2.text = it[i].balls
                            txtBatsmansFours2.text = it[i].fours
                            txtBatsmanSixes2.text = it[i].sixes

                            val dec = DecimalFormat("#0.00")

                            val sr: Double = (it[i].runs.toDouble() / it[i].balls.toDouble()) * 100

                            txtBatsmanStrikeRate2.text = dec.format(sr).toString()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * This method is used for convert balls to overs....
     */
    private fun convertBallsToOvers(balls: Int): Double {
        val round = Math.round((balls / 6).toFloat()).toString()
        val mod = (balls % 6).toString()
        return java.lang.Double.valueOf("$round.$mod")
    }
}
