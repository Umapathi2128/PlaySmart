package com.uma.playsmart.repository.singleton

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by Umapathi on 14/03/19.
 * Copyright Indyzen Inc, @2019
 */
class FirebaseReference {

    //    var mAuth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var isInningsComplete = false

    /**
     * This method gets database reference of firebase database...
     */
    fun getDbReference(): DatabaseReference {

        return if (database == null && reference == null) {
//            mAuth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            reference = database!!.reference
            reference!!
        } else reference!!
    }

    /**
     * This method gets database reference with UserID...
     */
    fun referenceWithUserID(matchId: String): DatabaseReference {

        return if (database == null && reference == null) {
//            mAuth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            reference = database!!.getReference(matchId)
            reference!!
        } else reference!!
    }

    /**
     * This method gets database reference with innings...
     */
    fun referenceWithInnings(innings: String, matchId: String): DatabaseReference {


        database = FirebaseDatabase.getInstance()
        reference = database!!.getReference(matchId).child(innings)
        return reference!!
//        return if (isInningsComplete) {
//            isInningsComplete = false
//            database = FirebaseDatabase.getInstance()
//            reference = database!!.getReference(matchId).child(innings)
//            reference!!
//        } else {
//            if (database == null && reference == null) {
//    //            mAuth = FirebaseAuth.getInstance()
//
//            } else reference!!
//        }
    }

//    fun referenceWithBatting(innings : String): DatabaseReference {
//
//        return if (database == null && reference == null) {
////            mAuth = FirebaseAuth.getInstance()
//            database = FirebaseDatabase.getInstance()
//            reference = database!!.getReference(mAppDataManager.getCurrentUserId()).child(innings)
//            reference!!
//        } else reference!!
//    }

}