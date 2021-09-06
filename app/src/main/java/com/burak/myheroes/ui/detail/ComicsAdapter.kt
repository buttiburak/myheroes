package com.burak.myheroes.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burak.myheroes.data.Comic
import com.burak.myheroes.databinding.ItemComicBinding


/**
 * Created by mburak on 6.09.2021.
 */
class ComicsAdapter(private var comics: MutableList<Comic>): RecyclerView.Adapter<ComicsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsItemViewHolder {
        val binding = ItemComicBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsItemViewHolder(binding)
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ComicsItemViewHolder, position: Int) {
        val currentComic = comics[position]
        holder.handleComic(currentComic)
    }

    fun submitList(recipeList: MutableList<Comic>) {
        comics.clear()
        comics.addAll(recipeList)
        notifyDataSetChanged()
    }
}