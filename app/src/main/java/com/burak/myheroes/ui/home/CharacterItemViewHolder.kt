package com.burak.myheroes.ui.home

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.databinding.ItemCharacterBinding
import com.burak.myheroes.util.ImageUtil
import com.burak.myheroes.util.TransitionUtil


/**
 * Created by mburak on 5.09.2021.
 */
class CharacterItemViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun handleCharacter(character: MarvelCharacter, onItemClickListener: OnItemClickListener, position: Int) {
        ViewCompat.setTransitionName(binding.characterItemImageView,
            TransitionUtil.getImageViewTransitionName(itemView.context, character.id))
        ViewCompat.setTransitionName(binding.characterItemNameTextView,
            TransitionUtil.getNameTextViewTransitionName(itemView.context, character.id))

        binding.characterItemNameTextView.text = character.name

        ImageUtil.setImageURL(binding.characterItemImageView, character.thumbnail.getThumbnailPath())

        itemView.setOnClickListener {
            onItemClickListener.onItemClicked(character,
                position,
                binding.characterItemImageView,
                binding.characterItemNameTextView)
        }
    }
}