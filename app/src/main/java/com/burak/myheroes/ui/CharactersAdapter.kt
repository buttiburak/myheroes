package com.burak.myheroes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.databinding.ItemCharacterBinding


/**
 * Created by mburak on 5.09.2021.
 */
class CharactersAdapter(private var characters: MutableList<MarvelCharacter>,
                        private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<CharacterItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = ItemCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterItemViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.handleCharacter(characters[position], onItemClickListener)
    }

    fun submitList(charactersList: MutableList<MarvelCharacter>) {
        characters.clear()
        characters.addAll(charactersList)
        notifyDataSetChanged()
    }

}