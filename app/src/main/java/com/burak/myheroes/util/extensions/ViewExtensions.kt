package com.burak.myheroes.util.extensions

import android.view.View


/**
 * Created by mburak on 5.09.2021.
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}