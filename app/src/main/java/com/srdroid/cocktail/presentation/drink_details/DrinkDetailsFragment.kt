package com.srdroid.cocktail.presentation.drink_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.srdroid.cocktail.databinding.FragmentDrinkDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    // ui
    private lateinit var _binding: FragmentDrinkDetailsBinding
    // view model
    private val viewModel: DrinkDetailsViewModel by viewModels()
    // nav args
    private val args: DrinkDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinkDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.drinkId?.let {
            viewModel.getDrinkDetails(it)
        }

        updateUIBasedOnResult()

    }

    private fun updateUIBasedOnResult() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.drinkDetails.collect { result ->
                // error state
                if (result.error.isNotBlank()) {
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                }
                // success state
                result.data?.let {
                    _binding.drinkDetails = it
                }
            }
        }
    }

}