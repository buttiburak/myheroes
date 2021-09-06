package com.burak.myheroes.ui.home

import android.widget.TextView
import com.burak.myheroes.data.MarvelCharacter
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by mburak on 5.09.2021.
 */
interface OnItemClickListener {
    fun onItemClicked(character: MarvelCharacter, position: Int,  draweeView: SimpleDraweeView, name: TextView)
}