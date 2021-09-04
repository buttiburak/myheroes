package com.burak.myheroes.ui

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.databinding.ItemCharacterBinding
import com.burak.myheroes.util.ImageUtil


/**
 * Created by mburak on 5.09.2021.
 */
class CharacterItemViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun handleCharacter(character: MarvelCharacter, onItemClickListener: OnItemClickListener) {
//            ViewCompat.setTransitionName(binding.characterItemImageView,
//                TransitionUtil.getImageViewTransitionName(itemView.context, character.id))
//            ViewCompat.setTransitionName(binding.characterItemNameTextView,
//                TransitionUtil.getTitleTextViewTransitionName(itemView.context, character.id))

        binding.characterItemNameTextView.text = character.name

        ImageUtil.setImageURL(binding.characterItemImageView, character.thumbnail.getFullPath())

        itemView.setOnClickListener {
            onItemClickListener.onItemClicked(character,
                binding.characterItemImageView,
                binding.characterItemNameTextView)
        }

    }
}