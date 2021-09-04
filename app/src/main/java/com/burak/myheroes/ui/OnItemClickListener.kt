package com.burak.myheroes.ui

import android.widget.TextView
import com.burak.myheroes.data.MarvelCharacter
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by mburak on 5.09.2021.
 */
interface OnItemClickListener {
    fun onItemClicked(character: MarvelCharacter, draweeView: SimpleDraweeView, name: TextView)
}