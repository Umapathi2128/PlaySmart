package com.uma.playsmart.extensions

import android.content.SharedPreferences

/**
 * Created by Umapathi on 06/03/19.
 * Copyright Indyzen Inc, @2019
 */

/**
 * inline function for edit shared preference
 */
inline fun SharedPreferences.edit(func:SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}