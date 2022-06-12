package com.srdroid.cocktail.presentation.drink_search

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.srdroid.cocktail.R
import com.srdroid.cocktail.databinding.FragmentDrinkSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DrinkSearchFragment : Fragment(), SearchView.OnQueryTextListener {

    // search adapter
    private val searchAdapter = DrinkSearchAdapter()

    // view model
    private val viewModel: DrinkSearchViewModel by viewModels()

    // ui
    private lateinit var binding: FragmentDrinkSearchBinding
    private lateinit var rootView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkSearchBinding.inflate(inflater, container, false)
        // initial search only first time
        if (!this::rootView.isInitialized)
            invokeInitialSearch()
        rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set updater
        binding.drinkSearchRecycler.apply {
            adapter = searchAdapter
        }

        // observe and update UI based on result
        updateUIBasedOnSearchResult()

        // set item click listener
        onItemClicked()
    }

    /**
     * Update UI based on results
     */
    private fun updateUIBasedOnSearchResult() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.drinkSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressDrinkSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressDrinkSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let { result ->
                    if (result.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressDrinkSearch.visibility = View.GONE
                    searchAdapter.setContentList(result.toMutableList())
                }

            }
        }
    }

    /**
     * Item Click listener
     */
    private fun onItemClicked() {
        searchAdapter.itemClickListener {
            findNavController().navigate(
                DrinkSearchFragmentDirections.actionDrinkSearchFragmentToDrinkDetailsFragment(
                    it.id, it.name
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cocktail, menu)
        val searchView = (menu.findItem(R.id.menuSearch).actionView as SearchView)
        // set listener
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuRefresh -> {
                invokeInitialSearch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun invokeInitialSearch() {
        viewModel.searchDrink("t")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.searchDrink(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}