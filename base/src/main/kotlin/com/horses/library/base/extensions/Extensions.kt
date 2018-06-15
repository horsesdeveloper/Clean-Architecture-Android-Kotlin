@file:Suppress("unused")

package com.horses.library.base.extensions

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

/**
 * @author @briansalvattore on 24/01/2018.
 */
fun Context.postDelayed(unit: () -> Unit, delay: Long) {
    Handler().postDelayed(unit, delay)
}

fun AppCompatActivity.startActivityPostDelayed(cls: Class<out AppCompatActivity>, delay: Long, vararg extras: Serializable) {
    val intent = Intent(this, cls)

    for (i in 0 until extras.size) {
        intent.putExtra("extra$i", extras[i])
    }

    postDelayed({ startActivity(intent) }, delay)
}

fun Any?.toInt(): Int {
    if (this == null) {
        return -1
    } else if (this is Double) {
        return this.toInt()
    } else if (this is String) {
        try {
            return Integer.parseInt(this)
        } catch (e: Exception) {
            /** maybe parse to double */
            return -1
        }
    } else {
        return Integer.parseInt(java.lang.String.valueOf(this))
    }
}

fun TextView.isEmpty() = getString().isEmpty()

fun TextView.getString() = text.toString().trim()

fun String?.safeNull() = this ?: ""

fun EditText.clean() = setText("")

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.setImageDrawable(@DrawableRes drawableRes: Int) = setImageDrawable(getDrawable(drawableRes))

fun AppCompatActivity.startActivity(cls: Class<out AppCompatActivity>, vararg extras: Serializable) {
    val intent = Intent(this, cls)

    for (i in 0 until extras.size) {
        intent.putExtra("extra$i", extras[i])
    }

    startActivity(intent)
}

fun String.firstCap() = this.substring(0, 1).toUpperCase() + this.substring(1, this.length).toLowerCase()

fun String.firstAllCaps():String {
    val builder = StringBuilder()

    for (part in this.split(" ")) {
        builder.append(part.firstCap())
        builder.append(" ")
    }

    return builder.toString().trim()
}

fun TextInputLayout.showError(@StringRes message: Int) {
    showError(context.getString(message))
}

fun TextInputLayout.showError(message: String?) {
    this.error = message
    this.getChildAt(1).visibility = View.VISIBLE
}

fun TextInputLayout.cleanError() {
    this.error = null
    this.getChildAt(1).visibility = View.GONE
}

fun TextInputLayout.enableError() {
    this.isErrorEnabled = true
    this.cleanError()
}

fun EditText.showError(@StringRes message: Int) {
    showError(context.getString(message))
}

fun EditText.showError(message: String) {
    val parent = this.parent.parent as TextInputLayout
    parent.showError(message)
}

fun EditText.cleanError() {
    val parent = this.parent.parent as TextInputLayout
    parent.cleanError()
}

fun EditText.enableError() {
    val parent = this.parent.parent as TextInputLayout
    parent.enableError()
}

fun EditText.addActionListener(unit: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        var handled = false

        if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
            unit()
            handled = true
        } else if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEND) {
            unit()
            handled = true
        }

        handled
    }
}

fun View.addOnGlobalLayoutListener(unit: () -> Unit) {
    val view = this
    view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            unit()
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

fun View.getDrawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(context, drawableRes)

fun View.getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)