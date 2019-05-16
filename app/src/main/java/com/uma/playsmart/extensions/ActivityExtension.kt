package com.uma.playsmart.extensions


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Umapathi on 06/03/19.
 * Copyright Indyzen Inc, @2019
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


 fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, fragmentId: String) {

    supportFragmentManager.inTransaction {
        replace(frameId, fragment, fragmentId)
//        addToBackStack(fragmentId)
    }
}

fun AppCompatActivity.popAllFragmentsFromBackStack() {

    for (i in 0 until supportFragmentManager.backStackEntryCount) {
        supportFragmentManager.popBackStackImmediate()
    }

}





