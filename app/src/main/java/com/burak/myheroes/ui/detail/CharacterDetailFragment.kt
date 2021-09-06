package com.burak.myheroes.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.data.helper.SimpleResult
import com.burak.myheroes.databinding.FragmentCharacterDetailBinding
import com.burak.myheroes.ui.HomeViewModel
import com.burak.myheroes.util.ImageUtil
import com.burak.myheroes.util.TransitionUtil
import com.burak.myheroes.util.extensions.hide
import com.burak.myheroes.util.extensions.show


/**
 * Created by mburak on 5.09.2021.
 */
class CharacterDetailFragment: Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var comicsAdapter: ComicsAdapter
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        comicsAdapter = ComicsAdapter(mutableListOf())

        binding.characterComicsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = comicsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    private fun observeViewModel() {
        homeViewModel.apply {
            selectedCharacters.observe(viewLifecycleOwner, {
                handleSelectedCharacter(it)
                homeViewModel.getComics(it.id)
            })

            comics.observe(viewLifecycleOwner, {
                when (it) {
                    is SimpleResult.Loading -> {
                        binding.comicsProgressBar.show()
                    }
                    is SimpleResult.Success -> {
                        binding.comicsProgressBar.hide()
                        comicsAdapter.submitList(it.data)
                    }
                    is SimpleResult.Error -> {
                        binding.comicsProgressBar.hide()
                    }
                }
            })
        }
    }

    private fun handleSelectedCharacter(character: MarvelCharacter) {
        ViewCompat.setTransitionName(binding.characterImageView,
            TransitionUtil.getImageViewTransitionName(requireContext(), character.id))
        ViewCompat.setTransitionName(binding.characterNameTextView,
            TransitionUtil.getNameTextViewTransitionName(requireContext(), character.id))

        ImageUtil.setImageURL(binding.characterImageView, character.thumbnail.getThumbnailPath())
        binding.characterNameTextView.text = character.name
        binding.characterDescriptionTextView.text = character.description
    }
}