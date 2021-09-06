package com.burak.myheroes.util

import android.content.Context
import com.burak.myheroes.R


/**
 * Created by mburak on 5.09.2021.
 */
object TransitionUtil {
    fun getImageViewTransitionName(context: Context, id: Int): String
            = context.getString(R.string.image_view_transition_name, id.toString())

    fun getNameTextViewTransitionName(context: Context, id: Int): String
            = context.getString(R.string.name_text_view_transition_name, id.toString())
}