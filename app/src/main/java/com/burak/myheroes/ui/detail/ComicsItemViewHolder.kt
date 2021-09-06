package com.burak.myheroes.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.burak.myheroes.data.Comic
import com.burak.myheroes.databinding.ItemComicBinding
import com.burak.myheroes.util.ImageUtil


/**
 * Created by mburak on 6.09.2021.
 */
class ComicsItemViewHolder(private val binding: ItemComicBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun handleComic(comic: Comic) {
        binding.comicItemNameTextView.text = comic.title

        ImageUtil.setImageURL(binding.comicItemImageView, comic.thumbnail.getThumbnailPath())
    }
}