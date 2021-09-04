package com.burak.myheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.data.helper.SimpleResult
import com.burak.myheroes.databinding.FragmentHomeBinding
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by mburak on 5.09.2021.
 */
class HomeFragment: Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var charactersAdapter: CharactersAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeSwipeToRefreshLayout.setOnRefreshListener {
            homeViewModel.getCharacters()
        }

        setupAdapter()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        charactersAdapter = CharactersAdapter(mutableListOf(), object : OnItemClickListener {
            override fun onItemClicked(character: MarvelCharacter, draweeView: SimpleDraweeView, title: TextView) {
//                homeViewModel.selectRecipe(recipe)
//                val extras = FragmentNavigatorExtras(
//                    draweeView to
//                            TransitionUtil.getImageViewTransitionName(requireContext(), recipe.contentfulId),
//                    title to
//                            TransitionUtil.getTitleTextViewTransitionName(requireContext(), recipe.contentfulId))
//                findNavController().navigate(R.id.action_from_home_to_recipe_detail, null, null, extras)
            }
        })

        binding.charactersView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = charactersAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun observeViewModel() {
        homeViewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it) {
                is SimpleResult.Loading -> {
                    binding.homeSwipeToRefreshLayout.isRefreshing = true
                }
                is SimpleResult.Success -> {
                    binding.homeSwipeToRefreshLayout.isRefreshing = false
                    if (it.data.isEmpty()) {
//                        binding.homeEmptyView.show()
                    } else {
//                        binding.homeEmptyView.hide()
                        charactersAdapter.submitList(it.data)
                    }
                }
                is SimpleResult.Error -> {
                    binding.homeSwipeToRefreshLayout.isRefreshing = false
                }
            }
        })
    }
}