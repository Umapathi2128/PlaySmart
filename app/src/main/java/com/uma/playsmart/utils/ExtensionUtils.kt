package com.uma.playsmart.utils

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.uma.playsmart.R

/**
 * Created by Umapathi on 25/03/19.
 * Copyright Indyzen Inc, @2019
 */
//fun showSnack(message: String, context: Activity?, color: String) {
//    val snackbar = context?.findViewById<View>(android.R.id.content)?.let { TSnackbar.make(it, message, TSnackbar.LENGTH_LONG) }
//    snackbar.setActionTextColor(Color.WHITE)
//    val snackbarView = snackbar.view
//    snackbarView.setBackgroundColor(Color.parseColor(color))
//    snackbar.setMaxWidth(3000)
//    val textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text) as? TextView
//    val gothamBookFont = ResourcesCompat.getFont(((context as? Context)!!), R.font.gotham_book)
//    textView?.typeface = gothamBookFont
//    textView?.setTextColor(Color.WHITE)
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//        textView?.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        textView?.gravity = Gravity.CENTER
//    } else {
//        textView?.gravity = Gravity.CENTER
//    }
//    snackbar.show()
//}
//
//fun showErrorSnack(message: String, context: Activity?) {
//    showSnack(message, context, "#f44a4a")
//}
//
//fun showSuccessSnack(message: String, context: Activity?) {
//    showSnack(message, context, "#05ba37")
//}

// Button to show a snack bar with action enabled


fun showSnackbar(view: View, message :String,bgColor: Int , txtColor : Int) {

    // Initialize a new snack bar instance
    val snackbar = Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_INDEFINITE
    )

    // Get the snack bar root view
    val snack_root_view = snackbar.view

    // Get the snack bar text view
    val snack_text_view = snack_root_view
        .findViewById<TextView>(R.id.snackbar_text)

    // Get the snack bar action view
    val snack_action_view = snack_root_view
        .findViewById<Button>(R.id.snackbar_action)

    // Change the snack bar root view background color
    snack_root_view.setBackgroundColor(Color.parseColor(bgColor.toString()))

    // Change the snack bar text view text color
    snack_text_view.setTextColor(txtColor)

    // Change snack bar text view text style
    snack_text_view.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)

    // Change the snack bar action button text color
//    snack_action_view.setTextColor(Color.YELLOW)
//
//    // Set an action for snack bar
//    snackbar.setAction("Hide Me") {
//        // Hide the snack bar
//        snackbar.dismiss()
//    }

    // Finally, display the snack bar
    snackbar.show()
}

fun showErrorSnack(view: View,message :String)
{
    showSnackbar(view  , message ,R.color.snackBackground,Color.RED)
}

fun showSuccessSnack(view: View,message :String)
{
    showSnackbar(view  , message , R.color.snackBackground,Color.BLUE)
}



